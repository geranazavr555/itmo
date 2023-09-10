package raft;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Raft algorithm implementation.
 * All functions are called from the single main thread.
 *
 * @author Georgiy Nazarov
 */
public class ProcessImplKek implements Process {
    private final Environment env;
    private final Storage storage;
    private final StateMachine machine;

    private ProcessType processType = ProcessType.FOLLOWER;
    private PersistentState persistentState;

    private int commitIndex = 0;
    private int lastApplied = 0;

    private int receivedHeartbeats = 0;

    private LeaderState leaderState = null;
    private CandidateState candidateState = null;

    private List<Command> receivedCommands = new LinkedList<>();

    private Integer leaderId = null;

    public ProcessImplKek(Environment env) {
        this.env = env;
        this.storage = env.getStorage();
        this.machine = env.getMachine();

        persistentState = storage.readPersistentState();

        setupFollower(null);
    }

    private void setupFollower(Integer leaderId) {
        processType = ProcessType.FOLLOWER;
        env.startTimeout(Timeout.ELECTION_TIMEOUT);
        receivedHeartbeats = 0;
        leaderState = null;
        candidateState = null;
        this.leaderId = leaderId;

        if (leaderId != null)
            sendDelayedCommands();
    }

    private void setupCandidate() {
        leaderState = null;
        candidateState = new CandidateState();
        receivedHeartbeats = 0;
        candidateState.votes++;

        processType = ProcessType.CANDIDATE;
        setCurrentTerm(currentTerm() + 1);
        setVotedFor(env.getProcessId());
        for (int i = 0; i < env.getNProcesses(); i++) {
            if (i + 1 != env.getProcessId())
                env.send(i + 1, new Message.RequestVoteRpc(currentTerm(), storage.readLastLogId()));
        }
        env.startTimeout(Timeout.ELECTION_TIMEOUT);
    }

    private void setupLeader() {
        leaderState = new LeaderState(env.getNProcesses(), storage.readLastLogId().getIndex());
        candidateState = null;
        receivedHeartbeats = 0;

        processType = ProcessType.LEADER;
        setCurrentTerm(currentTerm() + 1);
        setVotedFor(env.getProcessId());
        sendHeartbeats();

        for (Command receivedCommand : receivedCommands) {
            appendLogEntry(receivedCommand);
        }
        receivedCommands = new LinkedList<>();
        
        broadcastAppendEntries();
    }

    private void setCurrentTerm(int term) {
        persistentState = new PersistentState(term, votedFor());
        storage.writePersistentState(persistentState);
    }

    private int currentTerm() {
        return persistentState.getCurrentTerm();
    }

    private void setVotedFor(Integer votedFor) {
        persistentState = new PersistentState(currentTerm(), votedFor);
        storage.writePersistentState(persistentState);
    }

    private void sendDelayedCommands() {
        for (Command receivedCommand : receivedCommands) {
            env.send(leaderId, new Message.ClientCommandRpc(currentTerm(), receivedCommand));
        }
        receivedCommands = new LinkedList<>();
    }

    private Integer votedFor() {
        return persistentState.getVotedFor();
    }

    @Override
    public void onTimeout() {
        switch (processType) {
            case FOLLOWER:
                onHeartbeatTimeout();
                break;
            case CANDIDATE:
                onElectionTimeout();
                break;
            case LEADER:
                sendHeartbeats();
                break;
        }
    }

    private void sendHeartbeats() {
        for (int i = 0; i < env.getNProcesses(); i++) {
            if (i + 1 != env.getProcessId()) {
                env.send(i + 1,
                        new Message.AppendEntryRpc(currentTerm(), storage.readLastLogId(), commitIndex, null));
            }
        }
        env.startTimeout(Timeout.LEADER_HEARTBEAT_PERIOD);
    }

    private void onElectionTimeout() {
        setupCandidate();
    }

    private void onHeartbeatTimeout() {
        if (receivedHeartbeats == 0) {
            setupCandidate();
        } else {
            receivedHeartbeats = 0;
            env.startTimeout(Timeout.ELECTION_TIMEOUT);
        }
    }

    private void sendAppendEntry(int dstId) {
        if (dstId != env.getProcessId()) {
            int index = storage.readLastLogId().getIndex();
            if (index >= leaderState.nextIndex[dstId - 1]) {
                LogEntry prevLogEntry = storage.readLog(index - 1);
                LogId prevLogId = StorageKt.getSTART_LOG_ID();
                if (prevLogEntry != null)
                    prevLogId = prevLogEntry.getId();
                LogEntry logEntry = storage.readLog(index);
                env.send(dstId, new Message.AppendEntryRpc(currentTerm(), prevLogId, commitIndex, logEntry));
            }
        }
    }

    private void broadcastAppendEntries() {
        for (int i = 0; i < env.getNProcesses(); i++) {
            sendAppendEntry(i + 1);
        }
    }
    
    @Override
    public void onMessage(int srcId, @NotNull Message message) {
        if (message.getTerm() > currentTerm()) {
            setCurrentTerm(message.getTerm());
            setupFollower(message instanceof Message.AppendEntryRpc ? srcId : null);
        }

        if (message instanceof Message.AppendEntryRpc)
            onAppendEntryRpc(srcId, (Message.AppendEntryRpc) message);
        else if (message instanceof Message.AppendEntryResult)
            onAppendEntryResult(srcId, (Message.AppendEntryResult) message);
        else if (message instanceof Message.ClientCommandRpc)
            onClientCommandRpc(srcId, (Message.ClientCommandRpc) message);
        else if (message instanceof Message.ClientCommandResult)
            onClientCommandResult(srcId, (Message.ClientCommandResult) message);
        else if (message instanceof Message.RequestVoteRpc)
            onRequestVoteRpc(srcId, (Message.RequestVoteRpc) message);
        else if (message instanceof Message.RequestVoteResult)
            onRequestVoteResult(srcId, (Message.RequestVoteResult) message);
        else
            throw new IllegalArgumentException();
    }

    private void onRequestVoteResult(int srcId, Message.RequestVoteResult message) {
        if (message.getTerm() == currentTerm() && processType == ProcessType.CANDIDATE) {
            candidateState.votes++;
            if (candidateState.votes > env.getNProcesses() / 2) {
                setupLeader();
            }
        }
    }

    private void onRequestVoteRpc(int srcId, Message.RequestVoteRpc message) {
        if (message.getTerm() < currentTerm()) {
            env.send(srcId, new Message.RequestVoteResult(currentTerm(), false));
            return;
        }

        if ((votedFor() == null || votedFor() == srcId)
                && message.getLastLogId().compareTo(storage.readLastLogId()) >= 0) {
            setVotedFor(srcId);
            env.send(srcId, new Message.RequestVoteResult(currentTerm(), true));
            return;
        }

        env.send(srcId, new Message.RequestVoteResult(currentTerm(), false));
    }

    private void onClientCommandResult(int srcId, Message.ClientCommandResult message) {
        env.onClientCommandResult(message.getResult());
    }

    private void onClientCommandRpc(int srcId, Message.ClientCommandRpc message) {
        onClientCommand(message.getCommand());
    }

    private void onAppendEntryResult(int srcId, Message.AppendEntryResult message) {
        if (processType == ProcessType.LEADER) {
            if (message.getLastIndex() != null) {
                leaderState.nextIndex[srcId - 1] = message.getLastIndex() + 1;
                leaderState.matchIndex[srcId - 1] = message.getLastIndex();
                handleUpdateMatchIndexes();
            } else {
                leaderState.nextIndex[srcId - 1]--;
                sendAppendEntry(srcId);
            }
        }
    }

    private void handleUpdateMatchIndexes() {
        List<Integer> matchIndices = new ArrayList<>(env.getNProcesses());
        for (int i = 0; i < env.getNProcesses(); ++i)
            if (i + 1 != env.getProcessId()) {
                matchIndices.add(leaderState.matchIndex[i]);
            }

        final Predicate<Integer> checkN = n -> {
            if (n <= commitIndex) return false;
            int cnt = 0;
            for (Integer matchIndex : matchIndices) {
                if (matchIndex >= n) cnt++;
            }
            if (cnt <= env.getNProcesses() / 2) return false;
            LogEntry logEntry = storage.readLog(n);
            if (logEntry == null) return false;
            return logEntry.getId().getTerm() == currentTerm();
        };

        for (int n = commitIndex + 1; n < storage.readLastLogId().getIndex(); n++)
            if (checkN.test(n))
                setCommitIndex(n);
    }

    private void onAppendEntryRpc(int srcId, Message.AppendEntryRpc message) {
        if (message.getTerm() == currentTerm() && processType == ProcessType.CANDIDATE) {
            setupFollower(srcId);
        }

        if (message.getEntry() == null) {
            onHeartbeat(srcId, message);
            return;
        }

        if (message.getTerm() < currentTerm()) {
            env.send(srcId, new Message.AppendEntryResult(currentTerm(), null));
            return;
        }

        LogEntry logEntry = storage.readLog(message.getPrevLogId().getIndex());
        if (logEntry == null) {
            env.send(srcId, new Message.AppendEntryResult(currentTerm(), null));
            return;
        }

        if (leaderId == null) {
            leaderId = srcId;
            sendDelayedCommands();
        }

        if (logEntry.getId().getTerm() != message.getPrevLogId().getTerm()) {
            storage.appendLogEntry(message.getEntry());
            env.send(srcId, new Message.AppendEntryResult(currentTerm(), null));
            return;
        }

        storage.appendLogEntry(message.getEntry());
        env.send(srcId, new Message.AppendEntryResult(currentTerm(), storage.readLastLogId().getIndex()));

        if (commitIndex < message.getLeaderCommit())
            setCommitIndex(Math.min(message.getLeaderCommit(), message.getEntry().getId().getIndex()));
    }

    private void setCommitIndex(int newIndex) {
        commitIndex = newIndex;
        while (commitIndex > lastApplied) {
            applyLogEntry(lastApplied);
            lastApplied++;
        }
    }

    private void applyLogEntry(int lastApplied) {
        machine.apply(Objects.requireNonNull(storage.readLog(lastApplied)).getCommand());
    }

    private void onHeartbeat(int srcId, Message.AppendEntryRpc message) {
        if (processType == ProcessType.FOLLOWER) {
            receivedHeartbeats++;
            env.startTimeout(Timeout.ELECTION_TIMEOUT);
        }
    }

    @Override
    public void onClientCommand(@NotNull Command command) {
        switch (processType) {
            case LEADER:
                onLeaderClientCommand(command);
                break;
            case FOLLOWER:
                onFollowerClientCommand(command);
                break;
            case CANDIDATE:
                onCandidateClientCommand(command);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    private void onCandidateClientCommand(Command command) {
        receivedCommands.add(command);
    }

    private void onFollowerClientCommand(Command command) {
        if (leaderId == null)
            receivedCommands.add(command);
        else
            env.send(leaderId, new Message.ClientCommandRpc(currentTerm(), command));
    }

    private void appendLogEntry(Command command) {
        storage.appendLogEntry(new LogEntry(new LogId(storage.readLastLogId().getIndex(), currentTerm()), command));
    }

    private void onLeaderClientCommand(Command command) {
        appendLogEntry(command);
    }

    private enum ProcessType {
        LEADER, FOLLOWER, CANDIDATE
    }

    private static class LeaderState {
        private final int[] nextIndex;
        private final int[] matchIndex;

        private LeaderState(int n, int leaderLastLogIndex) {
            nextIndex = new int[n];
            for (int i = 0; i < n; i++) {
                nextIndex[i] = leaderLastLogIndex + 1;
            }
            matchIndex = new int[n];
        }
    }

    private static class CandidateState {
        private int votes = 0;
    }
}

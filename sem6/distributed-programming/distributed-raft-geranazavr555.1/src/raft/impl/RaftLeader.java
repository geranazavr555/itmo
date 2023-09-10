package raft.impl;

import org.jetbrains.annotations.NotNull;
import raft.ProcessImpl;
import raft.*;

import java.util.*;

public class RaftLeader extends RaftProcess<RaftLeader.State> {
    public RaftLeader(Environment env, ProcessImpl externalProcess, RaftProcessState prevState) {
        super(new State(env, prevState), externalProcess);
    }

    @Override
    public void init() {
        state.env.startTimeout(Timeout.LEADER_HEARTBEAT_PERIOD);
        broadcastHeartbeat();
        for (Command command : state.delayedCommands())
            onClientCommand(command);
        state.clearDelayedCommands();
    }

    @Override
    public void onTimeout() {
        state.env.startTimeout(Timeout.LEADER_HEARTBEAT_PERIOD);
        broadcastHeartbeat();
    }

    @Override
    public void onClientCommand(@NotNull Command command) {
        state.appendLogEntry(new LogEntry(
                new LogId(state.lastLogId().getIndex() + 1, state.currentTerm()),
                command
        ));
        broadcastLastLogEntry();
    }

    private void broadcastLastLogEntry() {
        LogId lastLogId = state.lastLogId();
        for (int i = 1; i <= state.env.getNProcesses(); i++) {
            if (i != state.env.getProcessId() && state.nextIndex[i - 1] == lastLogId.getIndex())
//            if (i != state.env.getProcessId())
                send(i, newAppendEntryRpc(lastLogId.getIndex()));
        }
    }

    @Override
    public void onRequestVoteResult(int srcId, Message.RequestVoteResult message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        }
    }

    @Override
    public void onRequestVoteRpc(int srcId, Message.RequestVoteRpc message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        } else {
            send(srcId, new Message.RequestVoteResult(state.currentTerm(), false));
        }
    }

    @Override
    public void onClientCommandResult(int srcId, Message.ClientCommandResult message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        } else
            state.env.onClientCommandResult(message.getResult());
    }

    @Override
    public void onClientCommandRpc(int srcId, Message.ClientCommandRpc message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        } else {
            onClientCommand(message.getCommand());
        }
    }

    private Message.AppendEntryRpc newAppendEntryRpc(int index) {
        LogEntry prevLogEntry = state.logEntry(index - 1);
        LogId prevLogId = (prevLogEntry != null ? prevLogEntry.getId() : StorageKt.getSTART_LOG_ID());
        return new Message.AppendEntryRpc(state.currentTerm(), prevLogId, state.commitIndex, state.logEntry(index));
    }

    @Override
    public void onAppendEntryResult(int srcId, Message.AppendEntryResult message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
            return;
        }

        if (message.getLastIndex() == null) {
            state.nextIndex[srcId - 1]--;
            if (state.nextIndex[srcId - 1] < 1)
                state.nextIndex[srcId - 1] = 1;
            send(srcId, newAppendEntryRpc(state.nextIndex[srcId - 1]));
        } else {
            state.nextIndex[srcId - 1] = message.getLastIndex() + 1;
            state.setMatchIndex(srcId - 1, message.getLastIndex());

            while (state.lastApplied < state.commitIndex) {
                state.lastApplied++;
                LogEntry logEntry = state.logEntry(state.lastApplied);
//                if (logEntry.getId().getTerm() == state.currentTerm())
                applyCommand(logEntry.getCommand(), logEntry.getId().getTerm() == state.currentTerm());
            }

            if (state.nextIndex[srcId - 1] <= state.lastLogId().getIndex()) {
                send(srcId, newAppendEntryRpc(state.nextIndex[srcId - 1]));
            }
        }
    }

    @Override
    public void onAppendEntryRpc(int srcId, Message.AppendEntryRpc message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        } else {
            send(srcId, new Message.AppendEntryResult(state.currentTerm(), null));
        }
    }

    private void broadcastHeartbeat() {
        broadcast(new Message.AppendEntryRpc(state.currentTerm(), state.lastLogId(), state.commitIndex, null));
    }

    static class State extends RaftProcessState {
        private final int[] nextIndex;
        private final int[] matchIndex;

        public State(Environment env, RaftProcessState prevState) {
            super(env, prevState);
            nextIndex = new int[env.getNProcesses()];
            matchIndex = new int[env.getNProcesses()];
            Arrays.fill(nextIndex, lastLogId().getIndex() + 1);
        }

        public void setMatchIndex(int i, int matchIndex) {
            this.matchIndex[i] = matchIndex;
            this.matchIndex[env.getProcessId() - 1] = lastLogId().getIndex();

            NavigableMap<Integer, Integer> matchIndexMap = new TreeMap<>();
            for (int j = 0; j < env.getNProcesses(); j++)
                matchIndexMap.put(this.matchIndex[j], matchIndexMap.getOrDefault(this.matchIndex[j], 0) + 1);

            for (int n = matchIndexMap.lastKey(); n > commitIndex; n--) {
                if (logEntry(n).getId().getTerm() != currentTerm())
                    continue;

                if (matchIndexMap.tailMap(n, true).values().stream().mapToInt(Integer::intValue).sum()
                        > env.getNProcesses() / 2) {
                    commitIndex = n;
                    break;
                }
            }
        }
    }
}

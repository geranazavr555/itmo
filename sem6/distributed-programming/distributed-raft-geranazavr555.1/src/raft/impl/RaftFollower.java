package raft.impl;

import org.jetbrains.annotations.NotNull;
import raft.ProcessImpl;
import raft.*;

public class RaftFollower extends RaftProcess<RaftFollower.State> {
    public RaftFollower(Environment env, ProcessImpl externalProcess, RaftProcessState prevState) {
        super(new State(env, prevState), externalProcess);
    }

    @Override
    public void init() {
        clearTimeout();
    }

    private void clearTimeout() {
        state.env.startTimeout(Timeout.ELECTION_TIMEOUT);
    }

    @Override
    public void onTimeout() {
        convertToCandidate();
    }

    @Override
    public void onClientCommand(@NotNull Command command) {
        if (state.leaderId != null)
            send(state.leaderId, new Message.ClientCommandRpc(state.currentTerm(), command));
        else
            state.addDelayedCommand(command);
    }

    @Override
    public void onRequestVoteResult(int srcId, Message.RequestVoteResult message) {
        tryUpdateTerm(srcId, message, false);
    }

    private void tryUpdateTerm(int srcId, Message message, boolean updateLeader) {
        if (message.getTerm() >= state.currentTerm()) {
            boolean changedTerm = false;
            if (message.getTerm() > state.currentTerm()) {
                state.setCurrentTerm(message.getTerm());
                changedTerm = true;
            }

            if (updateLeader && state.leaderId == null)
                setLeaderId(srcId, changedTerm);
        }
    }

    @Override
    public void onRequestVoteRpc(int srcId, Message.RequestVoteRpc message) {
        if (message.getTerm() < state.currentTerm()) {
            send(srcId, new Message.RequestVoteResult(state.currentTerm(), false));
            return;
        }

        int newTerm = state.currentTerm();
        boolean changedTerm = false;
        if (message.getTerm() >= state.currentTerm()) {
            if (message.getTerm() > state.currentTerm()) {
                newTerm = message.getTerm();
                changedTerm = true;
            }
        }

        boolean grant = (state.votedFor() == null || state.votedFor() == srcId)
                && message.getLastLogId().compareTo(state.lastLogId()) >= 0;
        if (grant) {
            if (changedTerm)
                state.setPersistentState(newTerm, srcId);
            else
                state.setVotedFor(srcId);
        } else if (changedTerm)
            state.setCurrentTerm(newTerm);

        send(srcId, new Message.RequestVoteResult(newTerm, grant));

        if (changedTerm)
            convertToFollower();
    }

    @Override
    public void onClientCommandResult(int srcId, Message.ClientCommandResult message) {
        tryUpdateTerm(srcId, message, true);
        state.env.onClientCommandResult(message.getResult());
    }

    @Override
    public void onClientCommandRpc(int srcId, Message.ClientCommandRpc message) {
        tryUpdateTerm(srcId, message, false);

        if (state.leaderId != null) {
            send(state.leaderId, new Message.ClientCommandRpc(state.currentTerm(), message.getCommand()));
        } else {
            state.addDelayedCommand(message.getCommand());
        }
    }

    @Override
    public void onAppendEntryResult(int srcId, Message.AppendEntryResult message) {
        tryUpdateTerm(srcId, message, false);
    }

    @Override
    public void onAppendEntryRpc(int srcId, Message.AppendEntryRpc message) {
        if (message.getTerm() < state.currentTerm()) {
            send(srcId, new Message.AppendEntryResult(state.currentTerm(), null));
            return;
        }

        {
            int remoteTerm = message.getTerm();

            if (state.currentTerm() < remoteTerm) {
                state.setCurrentTerm(remoteTerm);
            }

            if (remoteTerm == state.currentTerm())
                setLeaderId(srcId, true);

            clearTimeout();
        }

        LogEntry prevLogEntry = state.logEntry(message.getPrevLogId().getIndex());
        if (message.getPrevLogId().compareTo(StorageKt.getSTART_LOG_ID()) != 0) {
            if (prevLogEntry == null || prevLogEntry.getId().getTerm() != message.getPrevLogId().getTerm()) {
                send(srcId, new Message.AppendEntryResult(state.currentTerm(), null));
                return;
            }
        }

        LogEntry entry = message.getEntry();
        if (entry != null) {
            state.appendLogEntry(entry);

            if (message.getLeaderCommit() > state.commitIndex) {
                setCommitIndex(Math.min(message.getLeaderCommit(), entry.getId().getIndex()));
            } else {
                tryApply();
            }

            send(srcId, new Message.AppendEntryResult(state.currentTerm(), entry.getId().getIndex()));
        } else {

            if (message.getLeaderCommit() > state.commitIndex) {
                setCommitIndex(message.getLeaderCommit());
            } else {
                tryApply();
            }

            if (prevLogEntry == null)
                send(srcId, new Message.AppendEntryResult(state.currentTerm(), StorageKt.getSTART_LOG_ID().getIndex()));
            else
                send(srcId, new Message.AppendEntryResult(state.currentTerm(), prevLogEntry.getId().getIndex()));

        }
    }

    private void setCommitIndex(int newCommitIndex) {
        state.commitIndex = newCommitIndex;
        tryApply();
    }

    private void tryApply() {
        while (state.lastApplied < state.commitIndex) {
            LogEntry logEntry = state.logEntry(state.lastApplied + 1);
            if (logEntry != null) {
                state.lastApplied++;
                applyCommand(logEntry.getCommand(), false);
            } else
                break;
        }
    }

    private void setLeaderId(int leaderId, boolean clearTimeout) {
        state.leaderId = leaderId;
        for (Command command : state.delayedCommands())
            send(leaderId, new Message.ClientCommandRpc(state.currentTerm(), command));
        state.clearDelayedCommands();
        if (clearTimeout)
            state.env.startTimeout(Timeout.ELECTION_TIMEOUT);
    }

    static class State extends RaftProcessState {
        private Integer leaderId = null;

        public State(Environment env, RaftProcessState prevState) {
            super(env, prevState);
        }
    }
}

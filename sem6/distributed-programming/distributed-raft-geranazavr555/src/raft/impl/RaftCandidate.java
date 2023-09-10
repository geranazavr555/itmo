package raft.impl;

import org.jetbrains.annotations.NotNull;
import raft.ProcessImpl;
import raft.*;

public class RaftCandidate extends RaftProcess<RaftCandidate.State> {
    public RaftCandidate(Environment env, ProcessImpl externalProcess, RaftProcessState prevState) {
        super(new State(env, prevState), externalProcess);
    }

    @Override
    public void init() {
        state.addVote(state.env.getProcessId());
        state.setPersistentState(state.currentTerm() + 1, state.env.getProcessId());
        broadcast(new Message.RequestVoteRpc(state.currentTerm(), state.lastLogId()));
        state.env.startTimeout(Timeout.ELECTION_TIMEOUT);
    }


    @Override
    public void onTimeout() {
        convertToCandidate();
    }

    @Override
    public void onClientCommand(@NotNull Command command) {
        state.addDelayedCommand(command);
    }

    @Override
    public void onRequestVoteResult(int srcId, Message.RequestVoteResult message) {
        if (message.getTerm() < state.currentTerm())
            return;

        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
            return;
        }

        if (message.getVoteGranted()) {
            state.addVote(srcId);
            if (state.hasQuorum())
                convertToLeader();
        }
    }

    @Override
    public void onRequestVoteRpc(int srcId, Message.RequestVoteRpc message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
            return;
        }

        send(srcId, new Message.RequestVoteResult(state.currentTerm(), false));
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
        } else
            state.addDelayedCommand(message.getCommand());
    }

    @Override
    public void onAppendEntryResult(int srcId, Message.AppendEntryResult message) {
        if (message.getTerm() > state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        }
    }

    @Override
    public void onAppendEntryRpc(int srcId, Message.AppendEntryRpc message) {
        if (message.getTerm() >= state.currentTerm()) {
            state.setCurrentTerm(message.getTerm());
            convertToFollowerAndProceed(srcId, message);
        } else {
            send(srcId, new Message.AppendEntryResult(state.currentTerm(), null));
        }
    }

    static class State extends RaftProcessState {
        private final boolean[] votes;


        public State(Environment env, RaftProcessState prevState) {
            super(env, prevState);
            votes = new boolean[env.getNProcesses()];
        }

        public void addVote(int srcId) {
            votes[srcId - 1] = true;
        }

        public boolean hasQuorum() {
            int cnt = 0;
            for (int i = 0; i < env.getNProcesses(); i++) {
                if (votes[i])
                    cnt++;
            }
            return cnt > env.getNProcesses() / 2;
        }
    }
}

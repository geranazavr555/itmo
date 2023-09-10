package raft.impl;

import org.jetbrains.annotations.NotNull;
import raft.Command;
import raft.CommandResult;
import raft.Message;
import raft.ProcessImpl;

public abstract class RaftProcess<StateT extends RaftProcessState> {
    protected final StateT state;
    protected final ProcessImpl externalProcess;

    protected RaftProcess(StateT state, ProcessImpl externalProcess) {
        this.state = state;
        this.externalProcess = externalProcess;
    }

    protected void send(int dstId, Message message) {
        state.env.send(dstId, message);
    }

    protected void broadcast(Message message) {
        for (int i = 1; i <= state.env.getNProcesses(); i++) {
            if (i != state.env.getProcessId())
                send(i, message);
        }
    }

    protected void applyCommand(Command command, boolean currentTerm) {
        CommandResult result = state.applyCommand(command);
        if (this instanceof RaftLeader && currentTerm) {
            if (command.getProcessId() == state.env.getProcessId())
                state.env.onClientCommandResult(result);
            else
                send(command.getProcessId(), new Message.ClientCommandResult(state.currentTerm(), result));
        }
    }

    protected void convertToCandidate() {
        if (this instanceof RaftLeader)
            throw new IllegalStateException();

        externalProcess.changeImpl(new RaftCandidate(state.env, externalProcess, state));
    }

    protected void convertToFollowerAndProceed(int srcId, Message message) {
        if (this instanceof RaftFollower)
            throw new IllegalStateException();

        externalProcess.changeImpl(new RaftFollower(state.env, externalProcess, state));
        externalProcess.onMessage(srcId, message);
    }

    protected void convertToFollower() {
        externalProcess.changeImpl(new RaftFollower(state.env, externalProcess, state));
    }

    protected void convertToLeader() {
        if (!(this instanceof RaftCandidate))
            throw new IllegalStateException();

        externalProcess.changeImpl(new RaftLeader(state.env, externalProcess, state));
    }

    public abstract void init();

    public abstract void onTimeout();

    public abstract void onClientCommand(@NotNull Command command);

    public abstract void onRequestVoteResult(int srcId, Message.RequestVoteResult message);

    public abstract void onRequestVoteRpc(int srcId, Message.RequestVoteRpc message);

    public abstract void onClientCommandResult(int srcId, Message.ClientCommandResult message);

    public abstract void onClientCommandRpc(int srcId, Message.ClientCommandRpc message);

    public abstract void onAppendEntryResult(int srcId, Message.AppendEntryResult message);

    public abstract void onAppendEntryRpc(int srcId, Message.AppendEntryRpc message);
}

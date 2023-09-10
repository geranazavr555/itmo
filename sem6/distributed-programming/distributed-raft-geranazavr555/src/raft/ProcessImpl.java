package raft;

import org.jetbrains.annotations.NotNull;
import raft.impl.RaftFollower;
import raft.impl.RaftProcess;
import raft.impl.RaftProcessState;

/**
 * Raft algorithm implementation.
 * All functions are called from the single main thread.
 *
 * @author Georgiy Nazarov
 */
public class ProcessImpl implements Process {
    protected final Environment env;
    private RaftProcess<? extends RaftProcessState> impl;

    public ProcessImpl(Environment env) {
        this.env = env;
        this.impl = new RaftFollower(env, this, null);
        this.impl.init();
    }

    @Override
    public void onTimeout() {
        impl.onTimeout();
    }

    @Override
    public void onMessage(int srcId, @NotNull Message message) {
        if (message instanceof Message.AppendEntryRpc)
            impl.onAppendEntryRpc(srcId, (Message.AppendEntryRpc) message);
        else if (message instanceof Message.AppendEntryResult)
            impl.onAppendEntryResult(srcId, (Message.AppendEntryResult) message);
        else if (message instanceof Message.ClientCommandRpc)
            impl.onClientCommandRpc(srcId, (Message.ClientCommandRpc) message);
        else if (message instanceof Message.ClientCommandResult)
            impl.onClientCommandResult(srcId, (Message.ClientCommandResult) message);
        else if (message instanceof Message.RequestVoteRpc)
            impl.onRequestVoteRpc(srcId, (Message.RequestVoteRpc) message);
        else if (message instanceof Message.RequestVoteResult)
            impl.onRequestVoteResult(srcId, (Message.RequestVoteResult) message);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void onClientCommand(@NotNull Command command) {
        impl.onClientCommand(command);
    }

    public void changeImpl(RaftProcess<? extends RaftProcessState> newImpl) {
        this.impl = newImpl;
        this.impl.init();
    }
}

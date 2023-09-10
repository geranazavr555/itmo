package raft.impl;

import raft.*;

import java.util.LinkedList;
import java.util.List;

public abstract class RaftProcessState {
    protected final Environment env;

    protected int commitIndex;
    protected int lastApplied;

    protected List<Command> delayedCommands;

    protected RaftProcessState(Environment env, RaftProcessState prevState) {
        this.env = env;
        if (prevState != null) {
            this.delayedCommands = prevState.delayedCommands;
            this.commitIndex = prevState.commitIndex;
            this.lastApplied = prevState.lastApplied;
        } else {
            this.delayedCommands = new LinkedList<>();
            this.commitIndex = 0;
            this.lastApplied = 0;
        }
    }

    private Storage storage() {
        return env.getStorage();
    }

    public CommandResult applyCommand(Command command) {
        return env.getMachine().apply(command);
    }

    public LogId lastLogId() {
        return storage().readLastLogId();
    }

    public LogEntry logEntry(int index) {
        return storage().readLog(index);
    }

    public void appendLogEntry(LogEntry logEntry) {
        storage().appendLogEntry(logEntry);
    }

    private PersistentState persistentState() {
        return storage().readPersistentState();
    }

    public int currentTerm() {
        return persistentState().getCurrentTerm();
    }

    public void setCurrentTerm(int term) {
        if (currentTerm() != term)
            storage().writePersistentState(new PersistentState(term, null));
    }

    public void setPersistentState(int term, Integer votedFor) {
        boolean change = false;

        if (votedFor == null) {
            if (votedFor() != null)
                change = true;
        } else {
            if (votedFor() == null)
                change = true;
            else
                change = !votedFor.equals(votedFor());
        }

        change |= currentTerm() != term;

        if (change)
            storage().writePersistentState(new PersistentState(term, votedFor));
    }

    public Integer votedFor() {
        return persistentState().getVotedFor();
    }

    public void setVotedFor(Integer votedFor) {
        boolean change = false;

        if (votedFor == null) {
            if (votedFor() != null)
                change = true;
        } else {
            if (votedFor() == null)
                change = true;
            else
                change = !votedFor.equals(votedFor());
        }

        if (change)
            storage().writePersistentState(new PersistentState(currentTerm(), votedFor()));
    }

    public void addDelayedCommand(Command command) {
        delayedCommands.add(command);
    }

    public List<Command> delayedCommands() {
        return delayedCommands;
    }

    public void clearDelayedCommands() {
        delayedCommands = new LinkedList<>();
    }
}

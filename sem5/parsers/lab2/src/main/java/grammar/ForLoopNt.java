package grammar;

public enum ForLoopNt implements NonTerminal {
    FOR_LOOP("for_loop"),
    INIT("init"),
    COND("cond"),
    INC("inc");

    private final String humanReadable;

    ForLoopNt(String humanReadable) {
        this.humanReadable = humanReadable;
    }

    @Override
    public String humanReadable() {
        return "NonTerminal{" + humanReadable + "}";
    }

    @Override
    public boolean same(GrammarElement another) {
        if (another instanceof ForLoopNt forLoopNt) {
            return equals(forLoopNt);
        }
        return false;
    }
}

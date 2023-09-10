package grammar;

public interface GrammarElement {
    String humanReadable();

    boolean same(GrammarElement another);
}

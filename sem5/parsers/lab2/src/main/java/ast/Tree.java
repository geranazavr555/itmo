package ast;

import grammar.GrammarElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tree {
    private final GrammarElement grammarElement;
    private final List<Tree> children;

    public Tree(GrammarElement grammarElement, List<Tree> children) {
        this.grammarElement = grammarElement;
        this.children = children;
    }

    public Tree(GrammarElement grammarElement, Tree... children) {
        this.grammarElement = grammarElement;
        this.children = Arrays.asList(children);
    }

    public Tree(GrammarElement grammarElement) {
        this(grammarElement, Collections.emptyList());
    }

    public GrammarElement getGrammarElement() {
        return grammarElement;
    }

    public List<Tree> getChildren() {
        return children;
    }
}

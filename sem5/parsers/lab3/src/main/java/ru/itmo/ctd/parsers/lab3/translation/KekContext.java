package ru.itmo.ctd.parsers.lab3.translation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KekContext {
    private final KekContext parent;
    private final Map<String, KekVar> vars = new HashMap<>();
    private final List<KekContext> children = new LinkedList<>();

    protected KekContext(KekContext parent) {
        this.parent = parent;
    }

    public boolean containsName(String name) {
        return vars.containsKey(name) || (parent != null && parent.containsName(name));
    }

    public void addVar(KekVar kekVar) {
        if (containsName(kekVar.getName()))
            throw new RuntimeException();
        vars.put(kekVar.getName(), kekVar);
    }

    public KekContext newChildContext() {
        KekContext kekContext = new KekContext(this);
        children.add(kekContext);
        return kekContext;
    }

    public KekContext getParent() {
        return parent;
    }

    public void ensureVar(String name) {
        if (!containsName(name))
            throw new RuntimeException();
    }
}

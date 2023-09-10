package ru.itmo.ctd.parsers.lab3.translation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KekGlobalContext extends KekContext {
    private final Map<String, KekFunction> functions = new HashMap<>();

    public KekGlobalContext() {
        super(null);
    }

    @Override
    public boolean containsName(String name) {
        return super.containsName(name) || functions.containsKey(name);
    }

    public void addFunction(KekFunction kekFunction) {
        if (containsName(kekFunction.getName()))
            throw new RuntimeException();
        functions.put(kekFunction.getName(), kekFunction);
    }

    public void ensureFunction(String name, List<TranslationUnit> args) {
        if (!functions.containsKey(name))
            throw new RuntimeException();
    }

    public Collection<KekFunction> getFunctions() {
        return functions.values();
    }
}

package ru.itmo.ctd.parsers.lab3.translation;

import ru.itmo.ctd.parsers.lab3.grammar.gen.KekBaseListener;
import ru.itmo.ctd.parsers.lab3.grammar.gen.KekParser;

public class KekContextManager extends KekBaseListener {
    private final KekGlobalContext globalContext = new KekGlobalContext();
    private KekContext context = globalContext;

    @Override
    public void enterBlock(KekParser.BlockContext ctx) {
        context = context.newChildContext();
    }

    @Override
    public void exitBlock(KekParser.BlockContext ctx) {
        context = context.getParent();
    }

    public KekContext getContext() {
        return context;
    }

    public KekGlobalContext getGlobalContext() {
        return globalContext;
    }
}

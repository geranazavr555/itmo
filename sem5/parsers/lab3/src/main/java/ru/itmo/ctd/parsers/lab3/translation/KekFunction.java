package ru.itmo.ctd.parsers.lab3.translation;

import java.util.List;
import java.util.stream.Collectors;

public class KekFunction implements TranslationUnit {
    private final String name;
    private final KekType returnType;
    private final List<KekVar> args;
    private final TranslationUnit content;

    public KekFunction(String name, KekType returnType, List<KekVar> args, TranslationUnit content) {
        this.name = name;
        this.returnType = returnType;
        this.args = args;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public KekType getReturnType() {
        return returnType;
    }

    public List<KekVar> getArgs() {
        return args;
    }

    public String getCDecl() {
        return returnType.getC() + " " + name + "("
                + args.stream().map(KekVar::getC).collect(Collectors.joining(", ")) + ")";
    }

    @Override
    public String getC() {
        return getCDecl() + "{\n" + content.getC() + "\n}\n";
    }
}

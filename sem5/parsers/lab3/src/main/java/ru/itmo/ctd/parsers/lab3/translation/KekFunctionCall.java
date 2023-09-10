package ru.itmo.ctd.parsers.lab3.translation;

import java.util.List;

public class KekFunctionCall implements TranslationUnit {
    private final String funcName;
    private final List<TranslationUnit> args;

    public KekFunctionCall(String funcName, List<TranslationUnit> args) {
        this.funcName = funcName;
        this.args = args;
    }

    @Override
    public String getC() {
        StringBuilder builder = new StringBuilder(funcName).append("(");

        for (TranslationUnit arg : args) {
            builder.append(arg.getC()).append(", ");
        }
        builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);

        return builder.append(")").toString();
    }
}

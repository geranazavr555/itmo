package ru.itmo.ctd.parsers.lab3.translation;

import java.util.List;

public class KekFile implements TranslationUnit {
    private final KekGlobalContext globalContext;

    public KekFile(KekGlobalContext globalContext) {
        this.globalContext = globalContext;
    }

    @Override
    public String getC() {
        StringBuilder builder = new StringBuilder("#include <bits/stdc++.h>\n\n");

        for (KekFunction function : globalContext.getFunctions()) {
            builder.append(function.getCDecl()).append(";\n");
        }



        return builder.toString();
    }
}

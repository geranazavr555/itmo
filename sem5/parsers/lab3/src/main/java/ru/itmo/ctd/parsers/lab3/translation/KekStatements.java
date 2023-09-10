package ru.itmo.ctd.parsers.lab3.translation;

import java.util.ArrayList;
import java.util.List;

public class KekStatements implements TranslationUnit {
    private List<TranslationUnit> statements;

    public KekStatements(List<TranslationUnit> statements) {
        this.statements = statements;
    }

    @Override
    public String getC() {
        return null;
    }
}

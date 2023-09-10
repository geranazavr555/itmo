package ru.itmo.ctd.parsers.lab3.translation;

import java.util.List;

public class KekBlock implements TranslationUnit {
    private final List<TranslationUnit> content;

    public KekBlock(List<TranslationUnit> content) {
        this.content = content;
    }

    @Override
    public String getC() {
        StringBuilder builder = new StringBuilder();
        for (TranslationUnit translationUnit : content) {
            builder.append(translationUnit.getC()).append(';');
        }
        return builder.toString();
    }
}

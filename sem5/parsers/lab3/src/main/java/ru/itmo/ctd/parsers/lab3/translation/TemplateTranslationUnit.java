package ru.itmo.ctd.parsers.lab3.translation;

import java.util.Collections;
import java.util.List;

public class TemplateTranslationUnit implements TranslationUnit {
    private final String template;
    private final List<TranslationUnit> args;

    public TemplateTranslationUnit(String template, List<TranslationUnit> args) {
        this.template = template;
        this.args = args;
    }

    public TemplateTranslationUnit(String template, TranslationUnit... args) {
        this.template = template;
        this.args = List.of(args);
    }

    public TemplateTranslationUnit(String template) {
        this(template, Collections.emptyList());
    }

    @Override
    public String getC() {
        String[] parts = template.split("\\$");
        if (parts.length != args.size() + 1)
            throw new RuntimeException();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.size(); i++) {
            builder.append(parts[i]).append(args.get(i).getC());
        }
        return builder.append(parts[parts.length - 1]).append(';').toString();
    }
}

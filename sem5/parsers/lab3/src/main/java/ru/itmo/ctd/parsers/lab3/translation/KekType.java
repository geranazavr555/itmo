package ru.itmo.ctd.parsers.lab3.translation;

public enum KekType implements TranslationUnit {
    INT("long long"), BOOL("bool");

    private final String c;

    KekType(String c) {
        this.c = c;
    }

    @Override
    public String getC() {
        return c;
    }
}

package ru.itmo.ctd.parsers.lab3.translation;

public class KekVar implements TranslationUnit {
    private final KekType type;
    private final String name;

    public KekVar(KekType type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public KekType getType() {
        return type;
    }

    @Override
    public String getC() {
        return type.getC() + " " + name;
    }
}

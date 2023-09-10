package kek.translation;

public record KekVar(KekPrimitiveType type, String name) implements Unit {
    @Override
    public String getCString() {
        return type.getCString() + " " + name;
    }
}

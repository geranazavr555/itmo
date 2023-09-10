package kek.translation;

public record KekVarUsage(KekVar kekVar) implements KekExpr {
    @Override
    public String getCString() {
        return kekVar.name();
    }

    @Override
    public KekPrimitiveType getType() {
        return kekVar.type();
    }
}

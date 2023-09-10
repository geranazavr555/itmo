package kek.translation;

public record KekLiteral(KekPrimitiveType type, String kekValue) implements KekExpr {
    @Override
    public String getCString() {
        return kekValue;
    }

    @Override
    public KekPrimitiveType getType() {
        return type;
    }
}

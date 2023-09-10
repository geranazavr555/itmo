package kek.translation;

import java.util.*;

import static kek.translation.KekPrimitiveType.*;

public enum KekOperatorRule {
    NOT(Associativity.NONE, 1, v("~", INT, INT), v("~", BOOL, BOOL)),
    MUL(Associativity.LEFT, 2, v("*", INT, INT, INT)),
    DIV(Associativity.LEFT, 2, v("/", INT, INT, INT)),
    MOD(Associativity.LEFT, 2, v("%", INT, INT, INT)),
    PLUS(Associativity.LEFT, 2, v("+", INT, INT, INT)),
    MINUS(Associativity.LEFT, 2, v("-", INT, INT, INT)),
    EQUALS(Associativity.LEFT, 2, v("==", BOOL, INT, INT), v("==", BOOL, BOOL, BOOL)),
    NOT_EQUALS(Associativity.LEFT, 2, v("!=", BOOL, INT, INT), v("!=", BOOL, BOOL, BOOL)),
    OR(Associativity.LEFT, 2, v("|", INT, INT, INT), v("||", BOOL, BOOL, BOOL)),
    AND(Associativity.LEFT, 2, v("&", INT, INT, INT), v("&&", BOOL, BOOL, BOOL));

    private final Associativity associativity;
    private final int arity;
    private final Map<List<KekPrimitiveType>, KekPrimitiveType> argsToResultType = new HashMap<>();
    private final Map<List<KekPrimitiveType>, String> argsToC = new HashMap<>();

    KekOperatorRule(Associativity associativity, int arity, KekOperatorRuleHolder... variants) {
        this.associativity = associativity;
        this.arity = arity;
        for (KekOperatorRuleHolder variant : variants) {
            assert variant.args().length == arity;
            List<KekPrimitiveType> args = List.of(variant.args());
            argsToC.put(args, variant.c());
            argsToResultType.put(args, variant.resultType());
        }
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public int getArity() {
        return arity;
    }

    public KekPrimitiveType getResultType(KekPrimitiveType... types) {
        return argsToResultType.get(List.of(types));
    }

    public boolean isApplicable(KekPrimitiveType... types) {
        return argsToResultType.containsKey(List.of(types));
    }

    public String getCString(KekPrimitiveType... types) {
        return argsToC.get(List.of(types));
    }

    private static KekOperatorRuleHolder v(String c, KekPrimitiveType resultType, KekPrimitiveType... args) {
        return new KekOperatorRuleHolder(c, resultType, args);
    }

    private record KekOperatorRuleHolder(String c, KekPrimitiveType resultType, KekPrimitiveType... args) {}

    public enum Associativity {
        NONE, LEFT, RIGHT;
    }
}

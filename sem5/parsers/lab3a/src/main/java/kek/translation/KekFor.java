package kek.translation;

import java.util.ArrayList;
import java.util.List;

public record KekFor(List<KekVar> vars, KekAssign init, KekExpr cond, KekAssign after, KekBlock block) implements KekStatement {
    @Override
    public List<String> getC() {
        if (cond.getType() != KekPrimitiveType.BOOL)
            throw new RuntimeException();

        List<String> result = new ArrayList<>();
        result.add("for (" + init.getCString() + "; " + cond.getCString() + "; " + after.getCString() + ")");
        result.addAll(block.getC());
        return result;
    }
}

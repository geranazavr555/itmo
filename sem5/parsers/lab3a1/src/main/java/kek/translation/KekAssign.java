package kek.translation;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public record KekAssign(List<KekVarUsage> kekVarUsages, List<KekExpr> kekExprs) implements KekStatement {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public List<String> getC() {
        if (kekVarUsages.size() == 1)
            return simple();

        List<String> result = new ArrayList<>();

        Map<String, String> varToTmpVar = new LinkedHashMap<>();
        for (int i = 0; i < kekVarUsages.size(); i++) {
            KekVar kekVar = kekVarUsages.get(i).kekVar();
            varToTmpVar.put(kekVar.name(), "tmp" + Math.abs(random.nextLong()));
            result.add(kekVar.type().getCString() + " " + varToTmpVar.get(kekVar.name()) + " = " + kekExprs.get(i).getCString() + ";");
        }

        for (KekVarUsage kekVarUsage : kekVarUsages) {
            KekVar kekVar = kekVarUsage.kekVar();
            result.add(kekVar.name() + " = " + varToTmpVar.get(kekVar.name()) + ";");
        }

        return result;
    }

    private List<String> simple() {
        return Collections.singletonList(kekVarUsages.get(0).getCString() + " = " + kekExprs.get(0).getCString() + ";");
    }
}

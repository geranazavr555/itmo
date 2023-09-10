package kek.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record KekFile(KekContextManager contextManager) implements Unit {
    @Override
    public List<String> getC() {
        List<String> result = new ArrayList<>();
        result.add("#include \"kek.h\"");
        result.add("");

        for (KekVar var : contextManager.getGlobalVars()) {
            result.add(var.getCString() + ";");
        }
        for (KekFunc func : contextManager.getGlobalFuncs()) {
            result.add(func.getCString() + ";");
        }

        Map<String, Block> funcsContent = contextManager.getGlobalContext().getFuncsContent();
        for (KekFunc func : contextManager.getGlobalFuncs()) {
            Block kekBlock = funcsContent.get(func.name());
            if (kekBlock == null)
                throw new RuntimeException("Could not find definition of the function '" + func.name() + "'");
            result.add(func.getCString());
            result.addAll(kekBlock.getC());
        }

        return result;
    }
}

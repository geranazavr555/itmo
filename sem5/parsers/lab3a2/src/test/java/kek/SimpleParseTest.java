package kek;

import kek.grammar.KekLexer;
import kek.grammar.KekParser;
import kek.translation.KekContextManager;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codehaus.plexus.util.IOUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

public class SimpleParseTest {
    @Test
    public void testSimple() {
        tryParseSimple(1);
        tryParseSimple(2);
    }

    private void tryParseSimple(int n) {
        System.err.println("test: simple " + n);
        tryParse("simple/c" + n + ".kek");
    }

    private void tryParse(String name) {
        KekLexer kekLexer = new KekLexer(CharStreams.fromString(readFile(name)));
        KekParser kekParser = new KekParser(new CommonTokenStream(kekLexer));

        KekContextManager kekContextManager = new KekContextManager();
        kekParser.addParseListener(kekContextManager);

        KekParser.FileContext file = kekParser.file(kekContextManager);
        if (file.exception != null)
            throw file.exception;
    }

    private String readFile(String name) {
        try {
            return IOUtil.toString(Objects.requireNonNull(getClass().getResourceAsStream("/" + name)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

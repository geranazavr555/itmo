import ast.Tree;
import exception.ParseException;
import grammar.ForLoopNt;
import grammar.GrammarElement;
import lex.Token;
import org.junit.Test;
import parser.Parser;

import static org.junit.Assert.*;
import static grammar.ForLoopNt.*;
import static lex.Token.Type.*;

public class ParserTest {
    private void assertSame(GrammarElement expected, GrammarElement actual) {
        assertTrue(expected.same(actual));
    }

    private void assertTree(Tree expected, Tree actual) {
        assertSame(expected.getGrammarElement(), actual.getGrammarElement());
        assertEquals(expected.getChildren().size(), actual.getChildren().size());
        for (int i = 0; i < expected.getChildren().size(); i++) {
            assertTree(expected.getChildren().get(i), actual.getChildren().get(i));
        }
    }

    private void test(String s, Tree expected) throws ParseException {
        assertTree(expected, Parser.parse(s));
    }

    @Test
    public void testValidParser() throws ParseException {
        test("for (int x = 0; x < 10; x++)", new Tree(FOR_LOOP,
                new Tree(new Token(FOR, "for")),
                new Tree(new Token(LPAREN, "(")),
                new Tree(
                        INIT,
                        new Tree(new Token(TYPE, "int")),
                        new Tree(new Token(VAR, "x")),
                        new Tree(new Token(EQ_SIGN, "=")),
                        new Tree(new Token(VALUE, "0"))
                ),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(
                        COND,
                        new Tree(new Token(VAR, "x")),
                        new Tree(new Token(COMPARE_SIGN, "<")),
                        new Tree(new Token(VALUE, "10"))
                ),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(
                        INC,
                        new Tree(new Token(VAR, "x")),
                        new Tree(new Token(INC_SIGN, "++"))
                ),
                new Tree(new Token(RPAREN, ")"))
        ));


        test("for (unsigned long int long\n\nkek = 100; kek >= -50; --kek)", new Tree(FOR_LOOP,
                new Tree(new Token(FOR, "for")),
                new Tree(new Token(LPAREN, "(")),
                new Tree(
                        INIT,
                        new Tree(new Token(TYPE, "unsigned long int long")),
                        new Tree(new Token(VAR, "kek")),
                        new Tree(new Token(EQ_SIGN, "=")),
                        new Tree(new Token(VALUE, "100"))
                ),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(
                        COND,
                        new Tree(new Token(VAR, "kek")),
                        new Tree(new Token(COMPARE_SIGN, ">=")),
                        new Tree(new Token(VALUE, "-50"))
                ),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(
                        INC,
                        new Tree(new Token(INC_SIGN, "--")),
                        new Tree(new Token(VAR, "kek"))
                ),
                new Tree(new Token(RPAREN, ")"))
        ));

        test("\n\nfor (\n;;)\n", new Tree(FOR_LOOP,
                new Tree(new Token(FOR, "for")),
                new Tree(new Token(LPAREN, "(")),
                new Tree(INIT),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(COND),
                new Tree(new Token(SEMICOLON, ";")),
                new Tree(INC),
                new Tree(new Token(RPAREN, ")"))
        ));
    }

    @Test(expected = ParseException.class)
    public void testInvalid1() throws ParseException {
        Parser.parse("");
    }

    @Test(expected = ParseException.class)
    public void testInvalid2() throws ParseException {
        Parser.parse("unsigned long");
    }

    @Test(expected = ParseException.class)
    public void testInvalid3() throws ParseException {
        Parser.parse("(unsigned long int long\n\nkek = 100; kek >= -50; --kek)");
    }

    @Test(expected = ParseException.class)
    public void testInvalid4() throws ParseException {
        Parser.parse("for ()");
    }

    @Test(expected = ParseException.class)
    public void testInvalid5() throws ParseException {
        Parser.parse("for (;)");
    }

    @Test(expected = ParseException.class)
    public void testInvalid6() throws ParseException {
        Parser.parse("$");
    }

    @Test(expected = ParseException.class)
    public void testInvalid7() throws ParseException {
        Parser.parse("for (unsigned long long long x = 5;;)");
    }

    @Test(expected = ParseException.class)
    public void testInvalid8() throws ParseException {
        Parser.parse("for (int int x = 5;;)");
    }
}

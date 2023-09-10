import lex.Token;
import org.junit.Test;

import static lex.Token.Type.*;
import static org.junit.Assert.assertEquals;

public class TokenTest {
    private void testAll(Token.Type tokenType, String string, boolean expected) {
        assert tokenType.getPattern() != null;
        assertEquals(expected, tokenType.getPattern().matcher(string).matches());
    }

    @Test
    public void testTokenPatterns() {
        testAll(FOR, "for", true);
        testAll(FOR, " for", false);
        testAll(FOR, "for ", false);
        testAll(LPAREN, "(", true);
        testAll(LPAREN, "()", false);
        testAll(LPAREN, ")", false);
        testAll(RPAREN, "(", false);
        testAll(RPAREN, "()", false);
        testAll(RPAREN, ")", true);
        testAll(SEMICOLON, ";", true);
        testAll(SEMICOLON, " ; ", false);
        testAll(END, "", true);
        testAll(END, "q", false);

        testAll(VAR, "abacaba", true);
        testAll(VAR, "abacaba012", true);
        testAll(VAR, "012", false);
        testAll(VAR, "12", false);
        testAll(VAR, "_abacab_a", true);

        testAll(VALUE, "123", true);
        testAll(VALUE, "0123", false);
        testAll(VALUE, "", false);
        testAll(VALUE, "+1", true);
        testAll(VALUE, "0", true);
        testAll(VALUE, "+   1", true);
        testAll(VALUE, "-   1", true);
        testAll(VALUE, "-1", true);
        testAll(VALUE, "100000000000", true);
        testAll(VALUE, "10000 0000000", false);
        testAll(VALUE, "10000++0000000", false);

        testAll(COMPARE_SIGN, ">=", true);
        testAll(COMPARE_SIGN, "<=", true);
        testAll(COMPARE_SIGN, ">", true);
        testAll(COMPARE_SIGN, "<", true);
        testAll(COMPARE_SIGN, "!=", true);
        testAll(COMPARE_SIGN, "==", true);
        testAll(COMPARE_SIGN, "> =", false);
        testAll(COMPARE_SIGN, "< =", false);
        testAll(COMPARE_SIGN, "> ", false);
        testAll(COMPARE_SIGN, "< ", false);
        testAll(COMPARE_SIGN, "! =", false);
        testAll(COMPARE_SIGN, "= =", false);

        testAll(INC_SIGN, "++", true);
        testAll(INC_SIGN, "--", true);
        testAll(INC_SIGN, "+ +", false);
        testAll(INC_SIGN, "- -", false);

        testAll(EQ_SIGN, "=", true);
        testAll(EQ_SIGN, "==", false);

        testAll(AND_SIGN, "&", false);
        testAll(AND_SIGN, "&&", true);
    }
}

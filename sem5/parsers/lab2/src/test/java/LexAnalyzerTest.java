import lex.LexAnalyzer;
import lex.Token;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static lex.Token.Type.*;

public class LexAnalyzerTest {
    private void typeTest(String s, Token.Type[] expectedTokenTypes) throws ParseException, IOException {
        LexAnalyzer lexer = new LexAnalyzer(s);
        List<Token.Type> tokenTypes = new ArrayList<>();
        Token token;
        do {
            token = lexer.nextToken();
            tokenTypes.add(token.getType());
        } while (token.getType() != Token.Type.END);
        assertArrayEquals(expectedTokenTypes, tokenTypes.toArray());
    }

    private void valueTest(String s, String[] expectedTokenValues) throws ParseException, IOException {
        LexAnalyzer lexer = new LexAnalyzer(s);
        List<String> tokenValues = new ArrayList<>();
        Token token;
        do {
            token = lexer.nextToken();
            tokenValues.add(token.getValue());
        } while (token.getType() != Token.Type.END);
        assertArrayEquals(expectedTokenValues, tokenValues.toArray());
    }

    @Test
    public void test() throws ParseException, IOException {
        typeTest("for (int x = 0; x < 10; x++)", new Token.Type[]{
                FOR, LPAREN, TYPE, VAR, EQ_SIGN, VALUE, SEMICOLON, VAR, COMPARE_SIGN, VALUE, SEMICOLON, VAR, INC_SIGN, RPAREN, END
        });
        typeTest("for\n(unsigned\nx = 0; x >= -10; --x)  \n", new Token.Type[]{
                FOR, LPAREN, TYPE, VAR, EQ_SIGN, VALUE, SEMICOLON, VAR, COMPARE_SIGN, VALUE, SEMICOLON, INC_SIGN, VAR, RPAREN, END
        });
        typeTest("for (int x = 0; x < 10; x++)", new Token.Type[]{
                FOR, LPAREN, TYPE, VAR, EQ_SIGN, VALUE, SEMICOLON, VAR, COMPARE_SIGN, VALUE, SEMICOLON, VAR, INC_SIGN, RPAREN, END
        });
        typeTest("long long int", new Token.Type[]{
                TYPE, END
        });
        typeTest("int long unsigned kek = 2;\n\nkek++;", new Token.Type[]{
                TYPE, VAR, EQ_SIGN, VALUE, SEMICOLON, VAR, INC_SIGN, SEMICOLON, END
        });

        valueTest("for (int x = 0; x < 10; x++)", new String[]{
                "for", "(", "int", "x", "=", "0", ";", "x", "<", "10", ";", "x", "++", ")", ""
        });
        valueTest("for\n(unsigned\nx = 0; x >= -10; --x)  \n", new String[]{
                "for", "(", "unsigned", "x", "=", "0", ";", "x", ">=", "-10", ";", "--", "x", ")", ""
        });
        valueTest("for (int x = 0; x < 10; x++)", new String[]{
                "for", "(", "int", "x", "=", "0", ";", "x", "<", "10", ";", "x", "++", ")", ""
        });
        valueTest("long long int", new String[]{
                "long long int", ""
        });
        valueTest("int long unsigned kek = 2;\n\nkek++;", new String[]{
                "int long unsigned", "kek", "=", "2", ";", "kek", "++", ";", ""
        });
    }
}

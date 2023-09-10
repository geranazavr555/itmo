package lex;

import grammar.GrammarElement;
import grammar.Terminal;

import javax.annotation.Nullable;
import java.util.regex.Pattern;

public record Token(lex.Token.Type type, String value) implements Terminal {
    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String humanReadable() {
        return "Token{" + type.name() + ", " + type.getHumanReadable() + ", " + value + "}";
    }

    @Override
    public boolean same(GrammarElement another) {
        if (another instanceof Token token) {
            return token.getType() == getType();
        }
        return false;
    }

    public enum Type {
        FOR("for", "'for'"),
        LPAREN("\\(", "'('"),
        RPAREN("\\)", "')'"),
        SEMICOLON(";", "';'"),
        END("$", "End of input"),
        TYPE(null, "Integer type"),
        VAR("[a-zA-Z_][a-zA-Z_0-9]*", "Variable identifier"),
        VALUE("((\\+|-)\s*)?(([1-9][0-9]*)|0)", "Decimal integer literal"),
        COMPARE_SIGN(">=|<=|>|<|!=|==", "Comparing operator"),
        INC_SIGN("\\+\\+|--", "Increment or decrement operator"),
        EQ_SIGN("=", "'='"),
        AND_SIGN("&&", "&&");

        @Nullable
        private final Pattern pattern;
        private final String humanReadable;

        Type(String regex, String humanReadable) {
            this.pattern = Pattern.compile("^" + regex, 0);
            this.humanReadable = humanReadable;
        }

        @Nullable
        public Pattern getPattern() {
            return pattern;
        }

        public String getHumanReadable() {
            return humanReadable;
        }
    }
}

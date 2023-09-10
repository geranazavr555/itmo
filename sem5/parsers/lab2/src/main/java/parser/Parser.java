package parser;

import ast.Tree;
import exception.ParseException;
import lex.LexAnalyzer;
import lex.Token;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static grammar.ForLoopNt.*;
import static lex.Token.Type.*;

public class Parser {
    private final LexAnalyzer lexer;
    private Token token;

    public Parser(Reader reader) {
        this.lexer = new LexAnalyzer(reader);
    }

    public Tree parse() throws IOException, ParseException {
        nextToken();
        Tree tree = forLoop();
        ensureTokenType(END);
        return tree;
    }

    private Token nextToken() throws IOException, ParseException {
        token = lexer.nextToken();
        return token;
    }

    @Contract("_ -> fail")
    private void throwParseException(Token.Type... types) throws ParseException {
        throw new ParseException(
                Arrays.stream(types)
                        .map(Token.Type::getHumanReadable)
                        .collect(Collectors.joining(" or ")) + " expected, but found " + token.getType().getHumanReadable(),
                lexer.getReadPos()
        );
    }

    private void ensureTokenType(Token.Type expectedTokenType) throws ParseException {
        if (token.getType() != expectedTokenType) {
            throwParseException(expectedTokenType);
        }
    }

    private Tree forLoop() throws ParseException, IOException {
        List<Tree> children = new ArrayList<>();

        children.add(new Tree(token));
        ensureTokenType(FOR);

        children.add(new Tree(nextToken()));
        ensureTokenType(LPAREN);

        nextToken();
        children.add(init());

        children.add(new Tree(token));
        ensureTokenType(SEMICOLON);

        nextToken();
        children.add(cond());

        children.add(new Tree(token));
        ensureTokenType(SEMICOLON);

        nextToken();
        children.add(inc());

        children.add(new Tree(token));
        ensureTokenType(RPAREN);

        nextToken();
        ensureTokenType(END);

        return new Tree(FOR_LOOP, children);
    }

    private Tree init() throws ParseException, IOException {
        return switch (token.getType()) {
            case TYPE -> {
                List<Tree> children = new ArrayList<>();
                children.add(new Tree(token));
                children.add(new Tree(nextToken()));
                ensureTokenType(VAR);
                children.add(new Tree(nextToken()));
                ensureTokenType(EQ_SIGN);
                children.add(new Tree(nextToken()));
                ensureTokenType(VALUE);
                nextToken();
                yield new Tree(INIT, children);
            }
            case SEMICOLON -> new Tree(INIT);
            default -> {
                throwParseException(TYPE, SEMICOLON);
                yield null;
            }
        };
    }

    private Tree cond() throws IOException, ParseException {
        return switch (token.getType()) {
            case VAR -> {
                List<Tree> children = new ArrayList<>();
                children.add(new Tree(token));
                children.add(new Tree(nextToken()));
                ensureTokenType(COMPARE_SIGN);
                children.add(new Tree(nextToken()));
                ensureTokenType(VALUE);
                nextToken();
                if (token.getType() == AND_SIGN) {
                    children.add(new Tree(token));
                    nextToken();
                    children.add(cond());
                }
                yield new Tree(COND, children);
            }
            case SEMICOLON -> new Tree(COND);
            default -> {
                throwParseException(VAR, SEMICOLON);
                yield null;
            }
        };
    }

    private Tree inc() throws IOException, ParseException {
        return switch (token.getType()) {
            case VAR -> {
                List<Tree> children = new ArrayList<>();
                children.add(new Tree(token));
                children.add(new Tree(nextToken()));
                ensureTokenType(INC_SIGN);
                nextToken();
                yield new Tree(INC, children);
            }
            case INC_SIGN -> {
                List<Tree> children = new ArrayList<>();
                children.add(new Tree(token));
                children.add(new Tree(nextToken()));
                ensureTokenType(VAR);
                nextToken();
                yield new Tree(INC, children);
            }
            case RPAREN -> new Tree(INC);
            default -> {
                throwParseException(VAR, INC_SIGN, RPAREN);
                yield null;
            }
        };
    }

    public static Tree parse(String s) throws ParseException {
        try {
            return new Parser(new StringReader(s)).parse();
        } catch (IOException e) {
            throw new AssertionError("Impossible case: IOException while parsing ready string", e);
        }
    }
}

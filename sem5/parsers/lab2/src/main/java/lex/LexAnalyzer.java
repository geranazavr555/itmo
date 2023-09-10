package lex;

import exception.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class LexAnalyzer {
    private static final String[] C_TYPE_KEYWORDS = new String[]{
            "int", "char", "signed", "unsigned", "long", "short"
    };

    private final Reader reader;
    private int readPos;
    private StringBuilder currentTokenValue;

    public LexAnalyzer(Reader reader) {
        this.reader = reader;
        readPos = 0;
        this.currentTokenValue = new StringBuilder();
    }

    public LexAnalyzer(CharSequence s) {
        this(new StringReader(s.toString()));
    }

    public int getReadPos() {
        return readPos;
    }

    public Token nextToken() throws IOException, ParseException {
        if (currentTokenValue.isEmpty()) {
            readNextTokenCandidate();
        }

        if (currentTokenValue.isEmpty()) {
            return new Token(Token.Type.END, "");
        }

        String tokenValue = currentTokenValue.toString();
        if (isCTypeKeyword(tokenValue)) {
            currentTokenValue = new StringBuilder();
            return parseCType(tokenValue, readPos);
        } else {
            for (Token.Type tokenType : Token.Type.values()) {
                if (tokenType.getPattern() != null) {
                    Matcher matcher = tokenType.getPattern().matcher(currentTokenValue);
                    if (matcher.find()) {
                        Token token = new Token(tokenType, currentTokenValue.substring(0, matcher.end()));
                        currentTokenValue.delete(0, matcher.end());
                        return token;
                    }
                }
            }
        }

        throw new ParseException("Unexpected token '" + tokenValue + "'", readPos);
    }

    private void readNextTokenCandidate() throws IOException {
        int codePoint;
        while ((codePoint = reader.read()) != -1) {
            readPos++;
            if (Character.isWhitespace(codePoint)) {
                if (currentTokenValue.isEmpty()) {
                    continue;
                } else {
                    break;
                }
            }

            currentTokenValue.appendCodePoint(codePoint);
        }
    }

    private Token parseCType(String typePrefix, int startPos) throws IOException, ParseException {
        List<String> typeWords = new ArrayList<>();
        typeWords.add(typePrefix);

        while (true) {
            readNextTokenCandidate();
            String typeWordCandidate = currentTokenValue.toString();
            if (isCTypeKeyword(typeWordCandidate)) {
                typeWords.add(typeWordCandidate);
                currentTokenValue = new StringBuilder();
            } else {
                break;
            }
        }

        checkCType(typeWords, startPos);
        return new Token(Token.Type.TYPE, String.join(" ", typeWords));
    }

    private void checkCType(List<String> typeWords, int startPos) throws ParseException {
        Map<String, Integer> counts = new HashMap<>();
        for (String typeWord : typeWords) {
            if (counts.containsKey(typeWord)) {
                counts.put(typeWord, counts.get(typeWord) + 1);
            } else {
                counts.put(typeWord, 1);
            }
        }

        for (Map.Entry<String, Integer> typeWordAndCount : counts.entrySet()) {
            String typeWord = typeWordAndCount.getKey();
            int count = typeWordAndCount.getValue();
            if (count > 2) {
                throw new ParseException("Incorrect type: more than 2 '" + typeWord + "'", startPos);
            } else if (count == 2 && !"long".equals(typeWord)) {
                throw new ParseException("Incorrect type: duplicated '" + typeWord + "'", startPos);
            }
        }
    }

    private boolean isCTypeKeyword(String s) {
        for (String cTypeKeyword : C_TYPE_KEYWORDS) {
            if (cTypeKeyword.equals(s)) {
                return true;
            }
        }
        return false;
    }
}

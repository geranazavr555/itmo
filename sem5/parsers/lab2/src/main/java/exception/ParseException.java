package exception;

public class ParseException extends java.text.ParseException {
    public ParseException(String s, int errorOffset) {
        super(s + " at position " + errorOffset, errorOffset);
    }
}

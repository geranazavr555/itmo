// Generated from C:/Programing/sem5/parsers/lab3a/src/main/antlr4\Kek.g4 by ANTLR 4.9.2
package kek.grammar;

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KekLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, COLON=2, INT=3, BOOL=4, CHAR=5, STRING=6, IMPORT=7, DEF=8, LPAR=9, 
		RPAR=10, COMMA=11, DEFER=12, LBRACE=13, RBRACE=14, BREAK=15, CONTINUE=16, 
		IF=17, ELSE=18, WHILE=19, ASSIGN=20, RETURN=21, EXTERN=22, OR=23, OR_SIGN_BITWISE=24, 
		OR_SIGN=25, AND=26, AND_SIGN_BITWISE=27, AND_SIGN=28, NOT=29, NOT_SIGN=30, 
		NOT_SIGN_BITWISE=31, PLUS_SIGN=32, MINUS_SIGN=33, MUL_SIGN=34, DIV=35, 
		DIV_SIGN=36, MOD=37, MOD_SIGN=38, EQUALS=39, NOT_EQUALS=40, INT_LITERAL=41, 
		BOOL_LITERAL=42, ID=43, EOLN=44, WS=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LET", "COLON", "INT", "BOOL", "CHAR", "STRING", "IMPORT", "DEF", "LPAR", 
			"RPAR", "COMMA", "DEFER", "LBRACE", "RBRACE", "BREAK", "CONTINUE", "IF", 
			"ELSE", "WHILE", "ASSIGN", "RETURN", "EXTERN", "OR", "OR_SIGN_BITWISE", 
			"OR_SIGN", "AND", "AND_SIGN_BITWISE", "AND_SIGN", "NOT", "NOT_SIGN", 
			"NOT_SIGN_BITWISE", "PLUS_SIGN", "MINUS_SIGN", "MUL_SIGN", "DIV", "DIV_SIGN", 
			"MOD", "MOD_SIGN", "EQUALS", "NOT_EQUALS", "INT_LITERAL", "BOOL_LITERAL", 
			"ID", "EOLN", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'let'", "':'", "'int'", "'bool'", "'char'", "'str'", "'import'", 
			"'def'", "'('", "')'", "','", "'defer'", "'{'", "'}'", "'break'", "'continue'", 
			"'if'", "'else'", "'while'", "'='", "'return'", "'extern'", "'or'", "'|'", 
			"'||'", "'and'", "'&'", "'&&'", "'not'", "'!'", "'~'", "'+'", "'-'", 
			"'*'", "'div'", "'/'", "'mod'", "'%'", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LET", "COLON", "INT", "BOOL", "CHAR", "STRING", "IMPORT", "DEF", 
			"LPAR", "RPAR", "COMMA", "DEFER", "LBRACE", "RBRACE", "BREAK", "CONTINUE", 
			"IF", "ELSE", "WHILE", "ASSIGN", "RETURN", "EXTERN", "OR", "OR_SIGN_BITWISE", 
			"OR_SIGN", "AND", "AND_SIGN_BITWISE", "AND_SIGN", "NOT", "NOT_SIGN", 
			"NOT_SIGN_BITWISE", "PLUS_SIGN", "MINUS_SIGN", "MUL_SIGN", "DIV", "DIV_SIGN", 
			"MOD", "MOD_SIGN", "EQUALS", "NOT_EQUALS", "INT_LITERAL", "BOOL_LITERAL", 
			"ID", "EOLN", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private KekContextManager contextManager;


	public KekLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Kek.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u011c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3$\3%\3%\3&\3&\3&\3&\3\'\3\'"+
		"\3(\3(\3(\3)\3)\3)\3*\5*\u00f0\n*\3*\6*\u00f3\n*\r*\16*\u00f4\3+\3+\3"+
		"+\3+\3+\3+\3+\3+\3+\5+\u0100\n+\3,\3,\7,\u0104\n,\f,\16,\u0107\13,\3-"+
		"\7-\u010a\n-\f-\16-\u010d\13-\3-\3-\7-\u0111\n-\f-\16-\u0114\13-\3.\6"+
		".\u0117\n.\r.\16.\u0118\3.\3.\2\2/\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/\3\2\6\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\2\u0122\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5a\3\2\2\2\7c\3\2"+
		"\2\2\tg\3\2\2\2\13l\3\2\2\2\rq\3\2\2\2\17u\3\2\2\2\21|\3\2\2\2\23\u0080"+
		"\3\2\2\2\25\u0082\3\2\2\2\27\u0084\3\2\2\2\31\u0086\3\2\2\2\33\u008c\3"+
		"\2\2\2\35\u008e\3\2\2\2\37\u0090\3\2\2\2!\u0096\3\2\2\2#\u009f\3\2\2\2"+
		"%\u00a2\3\2\2\2\'\u00a7\3\2\2\2)\u00ad\3\2\2\2+\u00af\3\2\2\2-\u00b6\3"+
		"\2\2\2/\u00bd\3\2\2\2\61\u00c0\3\2\2\2\63\u00c2\3\2\2\2\65\u00c5\3\2\2"+
		"\2\67\u00c9\3\2\2\29\u00cb\3\2\2\2;\u00ce\3\2\2\2=\u00d2\3\2\2\2?\u00d4"+
		"\3\2\2\2A\u00d6\3\2\2\2C\u00d8\3\2\2\2E\u00da\3\2\2\2G\u00dc\3\2\2\2I"+
		"\u00e0\3\2\2\2K\u00e2\3\2\2\2M\u00e6\3\2\2\2O\u00e8\3\2\2\2Q\u00eb\3\2"+
		"\2\2S\u00ef\3\2\2\2U\u00ff\3\2\2\2W\u0101\3\2\2\2Y\u010b\3\2\2\2[\u0116"+
		"\3\2\2\2]^\7n\2\2^_\7g\2\2_`\7v\2\2`\4\3\2\2\2ab\7<\2\2b\6\3\2\2\2cd\7"+
		"k\2\2de\7p\2\2ef\7v\2\2f\b\3\2\2\2gh\7d\2\2hi\7q\2\2ij\7q\2\2jk\7n\2\2"+
		"k\n\3\2\2\2lm\7e\2\2mn\7j\2\2no\7c\2\2op\7t\2\2p\f\3\2\2\2qr\7u\2\2rs"+
		"\7v\2\2st\7t\2\2t\16\3\2\2\2uv\7k\2\2vw\7o\2\2wx\7r\2\2xy\7q\2\2yz\7t"+
		"\2\2z{\7v\2\2{\20\3\2\2\2|}\7f\2\2}~\7g\2\2~\177\7h\2\2\177\22\3\2\2\2"+
		"\u0080\u0081\7*\2\2\u0081\24\3\2\2\2\u0082\u0083\7+\2\2\u0083\26\3\2\2"+
		"\2\u0084\u0085\7.\2\2\u0085\30\3\2\2\2\u0086\u0087\7f\2\2\u0087\u0088"+
		"\7g\2\2\u0088\u0089\7h\2\2\u0089\u008a\7g\2\2\u008a\u008b\7t\2\2\u008b"+
		"\32\3\2\2\2\u008c\u008d\7}\2\2\u008d\34\3\2\2\2\u008e\u008f\7\177\2\2"+
		"\u008f\36\3\2\2\2\u0090\u0091\7d\2\2\u0091\u0092\7t\2\2\u0092\u0093\7"+
		"g\2\2\u0093\u0094\7c\2\2\u0094\u0095\7m\2\2\u0095 \3\2\2\2\u0096\u0097"+
		"\7e\2\2\u0097\u0098\7q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7v\2\2\u009a"+
		"\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c\u009d\7w\2\2\u009d\u009e\7g\2\2"+
		"\u009e\"\3\2\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7h\2\2\u00a1$\3\2\2\2"+
		"\u00a2\u00a3\7g\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6"+
		"\7g\2\2\u00a6&\3\2\2\2\u00a7\u00a8\7y\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa"+
		"\7k\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac(\3\2\2\2\u00ad\u00ae"+
		"\7?\2\2\u00ae*\3\2\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2"+
		"\7v\2\2\u00b2\u00b3\7w\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7p\2\2\u00b5"+
		",\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7z\2\2\u00b8\u00b9\7v\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7p\2\2\u00bc.\3\2\2\2\u00bd"+
		"\u00be\7q\2\2\u00be\u00bf\7t\2\2\u00bf\60\3\2\2\2\u00c0\u00c1\7~\2\2\u00c1"+
		"\62\3\2\2\2\u00c2\u00c3\7~\2\2\u00c3\u00c4\7~\2\2\u00c4\64\3\2\2\2\u00c5"+
		"\u00c6\7c\2\2\u00c6\u00c7\7p\2\2\u00c7\u00c8\7f\2\2\u00c8\66\3\2\2\2\u00c9"+
		"\u00ca\7(\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7(\2\2\u00cc\u00cd\7(\2\2\u00cd"+
		":\3\2\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1\7v\2\2\u00d1"+
		"<\3\2\2\2\u00d2\u00d3\7#\2\2\u00d3>\3\2\2\2\u00d4\u00d5\7\u0080\2\2\u00d5"+
		"@\3\2\2\2\u00d6\u00d7\7-\2\2\u00d7B\3\2\2\2\u00d8\u00d9\7/\2\2\u00d9D"+
		"\3\2\2\2\u00da\u00db\7,\2\2\u00dbF\3\2\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de"+
		"\7k\2\2\u00de\u00df\7x\2\2\u00dfH\3\2\2\2\u00e0\u00e1\7\61\2\2\u00e1J"+
		"\3\2\2\2\u00e2\u00e3\7o\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7f\2\2\u00e5"+
		"L\3\2\2\2\u00e6\u00e7\7\'\2\2\u00e7N\3\2\2\2\u00e8\u00e9\7?\2\2\u00e9"+
		"\u00ea\7?\2\2\u00eaP\3\2\2\2\u00eb\u00ec\7#\2\2\u00ec\u00ed\7?\2\2\u00ed"+
		"R\3\2\2\2\u00ee\u00f0\7/\2\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f2\3\2\2\2\u00f1\u00f3\t\2\2\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5T\3\2\2\2\u00f6\u00f7"+
		"\7v\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7w\2\2\u00f9\u0100\7g\2\2\u00fa"+
		"\u00fb\7h\2\2\u00fb\u00fc\7c\2\2\u00fc\u00fd\7n\2\2\u00fd\u00fe\7u\2\2"+
		"\u00fe\u0100\7g\2\2\u00ff\u00f6\3\2\2\2\u00ff\u00fa\3\2\2\2\u0100V\3\2"+
		"\2\2\u0101\u0105\t\3\2\2\u0102\u0104\t\4\2\2\u0103\u0102\3\2\2\2\u0104"+
		"\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106X\3\2\2\2"+
		"\u0107\u0105\3\2\2\2\u0108\u010a\5[.\2\u0109\u0108\3\2\2\2\u010a\u010d"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2\2\2\u010d"+
		"\u010b\3\2\2\2\u010e\u0112\7\f\2\2\u010f\u0111\5[.\2\u0110\u010f\3\2\2"+
		"\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113Z"+
		"\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0117\t\5\2\2\u0116\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011a\u011b\b.\2\2\u011b\\\3\2\2\2\n\2\u00ef\u00f4\u00ff\u0105\u010b"+
		"\u0112\u0118\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
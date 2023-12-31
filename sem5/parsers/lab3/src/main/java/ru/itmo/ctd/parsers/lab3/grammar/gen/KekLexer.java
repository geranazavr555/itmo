// Generated from C:/Programing/sem5/parsers/lab3/src/main/java/ru/itmo/ctd/parsers/lab3/grammar\Kek.g4 by ANTLR 4.9.2
package ru.itmo.ctd.parsers.lab3.grammar.gen;

    import ru.itmo.ctd.parsers.lab3.translation.*;
    import java.util.stream.Collectors;

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
		INT_TYPE=1, BOOL_TYPE=2, COLON=3, RETURN=4, CONTINUE=5, BREAK=6, ELSE=7, 
		IF=8, WHILE=9, MOD=10, DIV=11, MUL=12, MINUS=13, PLUS=14, NOT=15, AND=16, 
		OR=17, LET=18, DEF=19, LPAR=20, RPAR=21, LBRACE=22, RBRACE=23, COMMA=24, 
		ASSIGN=25, BOOL=26, INT=27, ID=28, BLOCK_COMMENT=29, LINE_COMMENT_1=30, 
		LINE_COMMENT_2=31, EOLN=32, WS=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT_TYPE", "BOOL_TYPE", "COLON", "RETURN", "CONTINUE", "BREAK", "ELSE", 
			"IF", "WHILE", "MOD", "DIV", "MUL", "MINUS", "PLUS", "NOT", "AND", "OR", 
			"LET", "DEF", "LPAR", "RPAR", "LBRACE", "RBRACE", "COMMA", "ASSIGN", 
			"BOOL", "INT", "ID", "BLOCK_COMMENT", "LINE_COMMENT_1", "LINE_COMMENT_2", 
			"EOLN", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", "':'", "'return'", "'continue'", "'break'", 
			"'else'", "'if'", "'while'", "'%'", "'/'", "'*'", "'-'", "'+'", null, 
			null, null, "'let'", "'def'", "'('", "')'", "'{'", "'}'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT_TYPE", "BOOL_TYPE", "COLON", "RETURN", "CONTINUE", "BREAK", 
			"ELSE", "IF", "WHILE", "MOD", "DIV", "MUL", "MINUS", "PLUS", "NOT", "AND", 
			"OR", "LET", "DEF", "LPAR", "RPAR", "LBRACE", "RBRACE", "COMMA", "ASSIGN", 
			"BOOL", "INT", "ID", "BLOCK_COMMENT", "LINE_COMMENT_1", "LINE_COMMENT_2", 
			"EOLN", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00ed\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u0083\n"+
		"\20\3\21\3\21\3\21\3\21\5\21\u0089\n\21\3\22\3\22\3\22\5\22\u008e\n\22"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\5\33\u00ad\n\33\3\34\5\34\u00b0\n\34\3\34\6\34\u00b3\n\34\r\34\16"+
		"\34\u00b4\3\35\3\35\7\35\u00b9\n\35\f\35\16\35\u00bc\13\35\3\36\3\36\3"+
		"\36\3\36\7\36\u00c2\n\36\f\36\16\36\u00c5\13\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\7\37\u00d0\n\37\f\37\16\37\u00d3\13\37\3\37\3"+
		"\37\3 \3 \7 \u00d9\n \f \16 \u00dc\13 \3 \3 \3!\5!\u00e1\n!\3!\3!\5!\u00e5"+
		"\n!\3\"\6\"\u00e8\n\"\r\"\16\"\u00e9\3\"\3\"\3\u00c3\2#\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2"+
		"\b\4\2--//\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\2\u00f9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3"+
		"\2\2\2\5I\3\2\2\2\7N\3\2\2\2\tP\3\2\2\2\13W\3\2\2\2\r`\3\2\2\2\17f\3\2"+
		"\2\2\21k\3\2\2\2\23n\3\2\2\2\25t\3\2\2\2\27v\3\2\2\2\31x\3\2\2\2\33z\3"+
		"\2\2\2\35|\3\2\2\2\37\u0082\3\2\2\2!\u0088\3\2\2\2#\u008d\3\2\2\2%\u008f"+
		"\3\2\2\2\'\u0093\3\2\2\2)\u0097\3\2\2\2+\u0099\3\2\2\2-\u009b\3\2\2\2"+
		"/\u009d\3\2\2\2\61\u009f\3\2\2\2\63\u00a1\3\2\2\2\65\u00ac\3\2\2\2\67"+
		"\u00af\3\2\2\29\u00b6\3\2\2\2;\u00bd\3\2\2\2=\u00cb\3\2\2\2?\u00d6\3\2"+
		"\2\2A\u00e0\3\2\2\2C\u00e7\3\2\2\2EF\7k\2\2FG\7p\2\2GH\7v\2\2H\4\3\2\2"+
		"\2IJ\7d\2\2JK\7q\2\2KL\7q\2\2LM\7n\2\2M\6\3\2\2\2NO\7<\2\2O\b\3\2\2\2"+
		"PQ\7t\2\2QR\7g\2\2RS\7v\2\2ST\7w\2\2TU\7t\2\2UV\7p\2\2V\n\3\2\2\2WX\7"+
		"e\2\2XY\7q\2\2YZ\7p\2\2Z[\7v\2\2[\\\7k\2\2\\]\7p\2\2]^\7w\2\2^_\7g\2\2"+
		"_\f\3\2\2\2`a\7d\2\2ab\7t\2\2bc\7g\2\2cd\7c\2\2de\7m\2\2e\16\3\2\2\2f"+
		"g\7g\2\2gh\7n\2\2hi\7u\2\2ij\7g\2\2j\20\3\2\2\2kl\7k\2\2lm\7h\2\2m\22"+
		"\3\2\2\2no\7y\2\2op\7j\2\2pq\7k\2\2qr\7n\2\2rs\7g\2\2s\24\3\2\2\2tu\7"+
		"\'\2\2u\26\3\2\2\2vw\7\61\2\2w\30\3\2\2\2xy\7,\2\2y\32\3\2\2\2z{\7/\2"+
		"\2{\34\3\2\2\2|}\7-\2\2}\36\3\2\2\2~\177\7p\2\2\177\u0080\7q\2\2\u0080"+
		"\u0083\7v\2\2\u0081\u0083\7#\2\2\u0082~\3\2\2\2\u0082\u0081\3\2\2\2\u0083"+
		" \3\2\2\2\u0084\u0085\7c\2\2\u0085\u0086\7p\2\2\u0086\u0089\7f\2\2\u0087"+
		"\u0089\7(\2\2\u0088\u0084\3\2\2\2\u0088\u0087\3\2\2\2\u0089\"\3\2\2\2"+
		"\u008a\u008b\7q\2\2\u008b\u008e\7t\2\2\u008c\u008e\7~\2\2\u008d\u008a"+
		"\3\2\2\2\u008d\u008c\3\2\2\2\u008e$\3\2\2\2\u008f\u0090\7n\2\2\u0090\u0091"+
		"\7g\2\2\u0091\u0092\7v\2\2\u0092&\3\2\2\2\u0093\u0094\7f\2\2\u0094\u0095"+
		"\7g\2\2\u0095\u0096\7h\2\2\u0096(\3\2\2\2\u0097\u0098\7*\2\2\u0098*\3"+
		"\2\2\2\u0099\u009a\7+\2\2\u009a,\3\2\2\2\u009b\u009c\7}\2\2\u009c.\3\2"+
		"\2\2\u009d\u009e\7\177\2\2\u009e\60\3\2\2\2\u009f\u00a0\7.\2\2\u00a0\62"+
		"\3\2\2\2\u00a1\u00a2\7?\2\2\u00a2\64\3\2\2\2\u00a3\u00a4\7v\2\2\u00a4"+
		"\u00a5\7t\2\2\u00a5\u00a6\7w\2\2\u00a6\u00ad\7g\2\2\u00a7\u00a8\7h\2\2"+
		"\u00a8\u00a9\7c\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ad"+
		"\7g\2\2\u00ac\u00a3\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ad\66\3\2\2\2\u00ae"+
		"\u00b0\t\2\2\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2"+
		"\2\2\u00b1\u00b3\t\3\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b58\3\2\2\2\u00b6\u00ba\t\4\2\2"+
		"\u00b7\u00b9\t\5\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb:\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00be\7\61\2\2\u00be\u00bf\7,\2\2\u00bf\u00c3\3\2\2\2\u00c0\u00c2\13"+
		"\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\7,"+
		"\2\2\u00c7\u00c8\7\61\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\b\36\2\2\u00ca"+
		"<\3\2\2\2\u00cb\u00cc\7\61\2\2\u00cc\u00cd\7\61\2\2\u00cd\u00d1\3\2\2"+
		"\2\u00ce\u00d0\n\6\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf"+
		"\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\u00d5\b\37\2\2\u00d5>\3\2\2\2\u00d6\u00da\7%\2\2\u00d7\u00d9\n\6\2\2"+
		"\u00d8\u00d7\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db"+
		"\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\b \2\2\u00de"+
		"@\3\2\2\2\u00df\u00e1\5C\"\2\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e4\7\f\2\2\u00e3\u00e5\5C\"\2\u00e4\u00e3\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5B\3\2\2\2\u00e6\u00e8\t\7\2\2\u00e7\u00e6"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00ec\b\"\2\2\u00ecD\3\2\2\2\20\2\u0082\u0088\u008d"+
		"\u00ac\u00af\u00b4\u00ba\u00c3\u00d1\u00da\u00e0\u00e4\u00e9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
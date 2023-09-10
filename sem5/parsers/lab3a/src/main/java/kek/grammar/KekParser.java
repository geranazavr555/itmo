// Generated from C:/Programing/sem5/parsers/lab3a/src/main/antlr4\Kek.g4 by ANTLR 4.9.2
package kek.grammar;

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KekParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, COLON=2, SEMICOLON=3, INT=4, BOOL=5, CHAR=6, STRING=7, IMPORT=8, 
		DEF=9, LPAR=10, RPAR=11, COMMA=12, DEFER=13, LBRACE=14, RBRACE=15, BREAK=16, 
		CONTINUE=17, IF=18, ELSE=19, WHILE=20, ASSIGN=21, RETURN=22, EXTERN=23, 
		OR=24, OR_SIGN_BITWISE=25, OR_SIGN=26, AND=27, AND_SIGN_BITWISE=28, AND_SIGN=29, 
		NOT=30, NOT_SIGN=31, NOT_SIGN_BITWISE=32, PLUS_SIGN=33, MINUS_SIGN=34, 
		MUL_SIGN=35, DIV=36, DIV_SIGN=37, MOD=38, MOD_SIGN=39, EQUALS=40, NOT_EQUALS=41, 
		LBRACKET=42, RBRACKET=43, INDEX=44, FOR=45, INT_LITERAL=46, BOOL_LITERAL=47, 
		ID=48, EOLN=49, WS=50;
	public static final int
		RULE_file = 0, RULE_varTyped = 1, RULE_varDecl = 2, RULE_typeAnnotation = 3, 
		RULE_type = 4, RULE_arrayType = 5, RULE_primitiveType = 6, RULE_importDecl = 7, 
		RULE_moduleName = 8, RULE_funcDecl = 9, RULE_funcName = 10, RULE_funcArgsDecl = 11, 
		RULE_forArgsDecl = 12, RULE_funcModifiers = 13, RULE_funcModifier = 14, 
		RULE_externModifier = 15, RULE_block = 16, RULE_statement = 17, RULE_flowStatement = 18, 
		RULE_forStatement = 19, RULE_returnStatement = 20, RULE_breakStatement = 21, 
		RULE_continueStatement = 22, RULE_ifStatement = 23, RULE_ifPositivePart = 24, 
		RULE_ifNegativePart = 25, RULE_whileStatement = 26, RULE_simpleStatement = 27, 
		RULE_assignStatement = 28, RULE_assignStatementNoEoln = 29, RULE_var = 30, 
		RULE_varUsage = 31, RULE_expression = 32, RULE_operator = 33, RULE_orOperator = 34, 
		RULE_or = 35, RULE_andOperator = 36, RULE_and = 37, RULE_equalsOperator = 38, 
		RULE_equals = 39, RULE_plusOperator = 40, RULE_plus = 41, RULE_mulOperator = 42, 
		RULE_mul = 43, RULE_indexOperator = 44, RULE_index = 45, RULE_notOperator = 46, 
		RULE_not = 47, RULE_funcCall = 48, RULE_literal = 49, RULE_arrayLiteral = 50, 
		RULE_intLiteral = 51, RULE_boolLiteral = 52;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "varTyped", "varDecl", "typeAnnotation", "type", "arrayType", 
			"primitiveType", "importDecl", "moduleName", "funcDecl", "funcName", 
			"funcArgsDecl", "forArgsDecl", "funcModifiers", "funcModifier", "externModifier", 
			"block", "statement", "flowStatement", "forStatement", "returnStatement", 
			"breakStatement", "continueStatement", "ifStatement", "ifPositivePart", 
			"ifNegativePart", "whileStatement", "simpleStatement", "assignStatement", 
			"assignStatementNoEoln", "var", "varUsage", "expression", "operator", 
			"orOperator", "or", "andOperator", "and", "equalsOperator", "equals", 
			"plusOperator", "plus", "mulOperator", "mul", "indexOperator", "index", 
			"notOperator", "not", "funcCall", "literal", "arrayLiteral", "intLiteral", 
			"boolLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'let'", "':'", "';'", "'int'", "'bool'", "'char'", "'str'", "'import'", 
			"'def'", "'('", "')'", "','", "'defer'", "'{'", "'}'", "'break'", "'continue'", 
			"'if'", "'else'", "'while'", "'='", "'return'", "'extern'", "'or'", "'|'", 
			"'||'", "'and'", "'&'", "'&&'", "'not'", "'!'", "'~'", "'+'", "'-'", 
			"'*'", "'div'", "'/'", "'mod'", "'%'", "'=='", "'!='", "'['", "']'", 
			"'.'", "'for'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LET", "COLON", "SEMICOLON", "INT", "BOOL", "CHAR", "STRING", "IMPORT", 
			"DEF", "LPAR", "RPAR", "COMMA", "DEFER", "LBRACE", "RBRACE", "BREAK", 
			"CONTINUE", "IF", "ELSE", "WHILE", "ASSIGN", "RETURN", "EXTERN", "OR", 
			"OR_SIGN_BITWISE", "OR_SIGN", "AND", "AND_SIGN_BITWISE", "AND_SIGN", 
			"NOT", "NOT_SIGN", "NOT_SIGN_BITWISE", "PLUS_SIGN", "MINUS_SIGN", "MUL_SIGN", 
			"DIV", "DIV_SIGN", "MOD", "MOD_SIGN", "EQUALS", "NOT_EQUALS", "LBRACKET", 
			"RBRACKET", "INDEX", "FOR", "INT_LITERAL", "BOOL_LITERAL", "ID", "EOLN", 
			"WS"
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

	@Override
	public String getGrammarFileName() { return "Kek.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private KekContextManager contextManager;

	public KekParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public KekContextManager kekContextManager;
		public KekFile kekFile;
		public ImportDeclContext importDecl;
		public List<ImportDeclContext> importDecls = new ArrayList<ImportDeclContext>();
		public VarDeclContext varDecl;
		public List<VarDeclContext> varDecls = new ArrayList<VarDeclContext>();
		public FuncDeclContext funcDecl;
		public List<FuncDeclContext> funcDecls = new ArrayList<FuncDeclContext>();
		public TerminalNode EOF() { return getToken(KekParser.EOF, 0); }
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public List<FuncDeclContext> funcDecl() {
			return getRuleContexts(FuncDeclContext.class);
		}
		public FuncDeclContext funcDecl(int i) {
			return getRuleContext(FuncDeclContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FileContext(ParserRuleContext parent, int invokingState, KekContextManager kekContextManager) {
			super(parent, invokingState);
			this.kekContextManager = kekContextManager;
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file(KekContextManager kekContextManager) throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState(), kekContextManager);
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    contextManager = kekContextManager;

			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << IMPORT) | (1L << DEF))) != 0)) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(107);
					((FileContext)_localctx).importDecl = importDecl();
					((FileContext)_localctx).importDecls.add(((FileContext)_localctx).importDecl);
					}
					break;
				case LET:
					{
					setState(108);
					((FileContext)_localctx).varDecl = varDecl();
					((FileContext)_localctx).varDecls.add(((FileContext)_localctx).varDecl);
					}
					break;
				case DEF:
					{
					setState(109);
					((FileContext)_localctx).funcDecl = funcDecl();
					((FileContext)_localctx).funcDecls.add(((FileContext)_localctx).funcDecl);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(EOF);

			    ((FileContext)_localctx).kekFile =  new KekFile(contextManager);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypedContext extends ParserRuleContext {
		public KekVar kekVar;
		public TypeAnnotationContext typeAnnotation;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public VarTypedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varTyped; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarTyped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarTyped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarTyped(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypedContext varTyped() throws RecognitionException {
		VarTypedContext _localctx = new VarTypedContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varTyped);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			var();
			setState(119);
			((VarTypedContext)_localctx).typeAnnotation = typeAnnotation();

			    ((VarTypedContext)_localctx).kekVar =  new KekVar(((VarTypedContext)_localctx).typeAnnotation.kekType, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public VarTypedContext varTyped;
		public TerminalNode LET() { return getToken(KekParser.LET, 0); }
		public VarTypedContext varTyped() {
			return getRuleContext(VarTypedContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(LET);
			setState(123);
			((VarDeclContext)_localctx).varTyped = varTyped();
			setState(124);
			match(EOLN);

			    contextManager.addVar(((VarDeclContext)_localctx).varTyped.kekVar);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAnnotationContext extends ParserRuleContext {
		public KekType kekType;
		public TypeContext type;
		public TerminalNode COLON() { return getToken(KekParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterTypeAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitTypeAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitTypeAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAnnotationContext typeAnnotation() throws RecognitionException {
		TypeAnnotationContext _localctx = new TypeAnnotationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(COLON);
			setState(128);
			((TypeAnnotationContext)_localctx).type = type();
			((TypeAnnotationContext)_localctx).kekType =  ((TypeAnnotationContext)_localctx).type.kekType;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public KekType kekType;
		public PrimitiveTypeContext primitiveType;
		public ArrayTypeContext arrayType;
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				((TypeContext)_localctx).primitiveType = primitiveType();
				((TypeContext)_localctx).kekType =  ((TypeContext)_localctx).primitiveType.kekType;
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				((TypeContext)_localctx).arrayType = arrayType();
				((TypeContext)_localctx).kekType =  ((TypeContext)_localctx).arrayType.kekType;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public KekType kekType;
		public TypeContext type;
		public TerminalNode LBRACKET() { return getToken(KekParser.LBRACKET, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(KekParser.RBRACKET, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(LBRACKET);
			setState(140);
			((ArrayTypeContext)_localctx).type = type();
			setState(141);
			match(RBRACKET);
			((ArrayTypeContext)_localctx).kekType =  new KekArrayType(((ArrayTypeContext)_localctx).type.kekType);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public KekType kekType;
		public TerminalNode INT() { return getToken(KekParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(KekParser.BOOL, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primitiveType);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(INT);
				((PrimitiveTypeContext)_localctx).kekType =  KekPrimitiveType.INT;
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				match(BOOL);
				((PrimitiveTypeContext)_localctx).kekType =  KekPrimitiveType.BOOL;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(KekParser.IMPORT, 0); }
		public ModuleNameContext moduleName() {
			return getRuleContext(ModuleNameContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public TerminalNode EXTERN() { return getToken(KekParser.EXTERN, 0); }
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(IMPORT);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTERN) {
				{
				setState(151);
				match(EXTERN);
				}
			}

			setState(154);
			moduleName();
			setState(155);
			match(EOLN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public ModuleNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterModuleName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitModuleName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitModuleName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleNameContext moduleName() throws RecognitionException {
		ModuleNameContext _localctx = new ModuleNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_moduleName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclContext extends ParserRuleContext {
		public FuncNameContext funcName;
		public FuncArgsDeclContext funcArgsDecl;
		public TypeAnnotationContext typeAnnotation;
		public FuncModifiersContext funcModifiers;
		public List<FuncModifiersContext> fm = new ArrayList<FuncModifiersContext>();
		public BlockContext block;
		public List<BlockContext> blocks = new ArrayList<BlockContext>();
		public TerminalNode DEF() { return getToken(KekParser.DEF, 0); }
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public FuncArgsDeclContext funcArgsDecl() {
			return getRuleContext(FuncArgsDeclContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncModifiersContext funcModifiers() {
			return getRuleContext(FuncModifiersContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(DEF);
			setState(160);
			((FuncDeclContext)_localctx).funcName = funcName();
			setState(161);
			match(LPAR);
			setState(162);
			((FuncDeclContext)_localctx).funcArgsDecl = funcArgsDecl();
			setState(163);
			match(RPAR);
			setState(164);
			((FuncDeclContext)_localctx).typeAnnotation = typeAnnotation();
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFER || _la==EXTERN) {
				{
				setState(165);
				((FuncDeclContext)_localctx).funcModifiers = funcModifiers();
				((FuncDeclContext)_localctx).fm.add(((FuncDeclContext)_localctx).funcModifiers);
				}
			}

			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(168);
				((FuncDeclContext)_localctx).block = block(((FuncDeclContext)_localctx).funcArgsDecl.kekVars);
				((FuncDeclContext)_localctx).blocks.add(((FuncDeclContext)_localctx).block);
				}
				break;
			case EOLN:
				{
				setState(169);
				match(EOLN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			    KekFunc kekFunc = new KekFunc(
			        ((FuncDeclContext)_localctx).funcName.name,
			        ((FuncDeclContext)_localctx).typeAnnotation.kekType,
			        ((FuncDeclContext)_localctx).funcArgsDecl.kekVars
			    );
			    if (((FuncDeclContext)_localctx).fm.isEmpty() || !((FuncDeclContext)_localctx).fm.get(0).kekModifiers.contains(KekModifier.DEFER)) {
			        if (((FuncDeclContext)_localctx).fm.isEmpty() || !((FuncDeclContext)_localctx).fm.get(0).kekModifiers.contains(KekModifier.EXTERN)) {
			            contextManager.addFunc(kekFunc);
			            if (((FuncDeclContext)_localctx).blocks != null && ((FuncDeclContext)_localctx).blocks.size() == 1)
			                contextManager.addFuncContent(kekFunc, ((FuncDeclContext)_localctx).blocks.get(0).kekBlock);
			            else
			                throw new RuntimeException();
			        } else {
			            contextManager.addExternFunc(kekFunc);
			        }
			    } else {
			        contextManager.addDeferFunc(kekFunc);
			    }

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public String name;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(ID);
			((FuncNameContext)_localctx).name =  _localctx.getChild(0).getText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncArgsDeclContext extends ParserRuleContext {
		public List<KekVar> kekVars;
		public VarTypedContext varTyped;
		public List<VarTypedContext> vars = new ArrayList<VarTypedContext>();
		public List<VarTypedContext> varTyped() {
			return getRuleContexts(VarTypedContext.class);
		}
		public VarTypedContext varTyped(int i) {
			return getRuleContext(VarTypedContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public FuncArgsDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcArgsDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncArgsDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncArgsDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncArgsDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncArgsDeclContext funcArgsDecl() throws RecognitionException {
		FuncArgsDeclContext _localctx = new FuncArgsDeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_funcArgsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(177);
				((FuncArgsDeclContext)_localctx).varTyped = varTyped();
				((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(178);
					match(COMMA);
					setState(179);
					((FuncArgsDeclContext)_localctx).varTyped = varTyped();
					((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}


			    ((FuncArgsDeclContext)_localctx).kekVars =  ((FuncArgsDeclContext)_localctx).vars.stream().map(x -> x.kekVar).collect(Collectors.toList());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForArgsDeclContext extends ParserRuleContext {
		public List<KekVar> kekVars;
		public VarTypedContext varTyped;
		public List<VarTypedContext> vars = new ArrayList<VarTypedContext>();
		public List<VarTypedContext> varTyped() {
			return getRuleContexts(VarTypedContext.class);
		}
		public VarTypedContext varTyped(int i) {
			return getRuleContext(VarTypedContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public ForArgsDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forArgsDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterForArgsDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitForArgsDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitForArgsDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForArgsDeclContext forArgsDecl() throws RecognitionException {
		ForArgsDeclContext _localctx = new ForArgsDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forArgsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(189);
				((ForArgsDeclContext)_localctx).varTyped = varTyped();
				((ForArgsDeclContext)_localctx).vars.add(((ForArgsDeclContext)_localctx).varTyped);
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(190);
					match(COMMA);
					setState(191);
					((ForArgsDeclContext)_localctx).varTyped = varTyped();
					((ForArgsDeclContext)_localctx).vars.add(((ForArgsDeclContext)_localctx).varTyped);
					}
					}
					setState(196);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}


			    ((ForArgsDeclContext)_localctx).kekVars =  ((ForArgsDeclContext)_localctx).vars.stream().map(x -> x.kekVar).collect(Collectors.toList());
			    _localctx.kekVars.forEach(contextManager::addVar);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncModifiersContext extends ParserRuleContext {
		public Set<KekModifier> kekModifiers;
		public FuncModifierContext funcModifier;
		public List<FuncModifierContext> mods = new ArrayList<FuncModifierContext>();
		public List<FuncModifierContext> funcModifier() {
			return getRuleContexts(FuncModifierContext.class);
		}
		public FuncModifierContext funcModifier(int i) {
			return getRuleContext(FuncModifierContext.class,i);
		}
		public FuncModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncModifiersContext funcModifiers() throws RecognitionException {
		FuncModifiersContext _localctx = new FuncModifiersContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(201);
				((FuncModifiersContext)_localctx).funcModifier = funcModifier();
				((FuncModifiersContext)_localctx).mods.add(((FuncModifiersContext)_localctx).funcModifier);
				}
				}
				setState(204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DEFER || _la==EXTERN );

			    ((FuncModifiersContext)_localctx).kekModifiers =  ((FuncModifiersContext)_localctx).mods.stream().map(x -> x.kekModifier).collect(Collectors.toSet());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncModifierContext extends ParserRuleContext {
		public KekModifier kekModifier;
		public TerminalNode DEFER() { return getToken(KekParser.DEFER, 0); }
		public ExternModifierContext externModifier() {
			return getRuleContext(ExternModifierContext.class,0);
		}
		public FuncModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncModifierContext funcModifier() throws RecognitionException {
		FuncModifierContext _localctx = new FuncModifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funcModifier);
		try {
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFER:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(DEFER);
				((FuncModifierContext)_localctx).kekModifier =  KekModifier.DEFER;
				}
				break;
			case EXTERN:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				externModifier();
				((FuncModifierContext)_localctx).kekModifier =  KekModifier.EXTERN;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExternModifierContext extends ParserRuleContext {
		public TerminalNode EXTERN() { return getToken(KekParser.EXTERN, 0); }
		public ExternModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterExternModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitExternModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitExternModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternModifierContext externModifier() throws RecognitionException {
		ExternModifierContext _localctx = new ExternModifierContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_externModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(EXTERN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<KekVar> additionalVars;
		public KekBlock kekBlock;
		public StatementContext statement;
		public List<StatementContext> statements = new ArrayList<StatementContext>();
		public TerminalNode LBRACE() { return getToken(KekParser.LBRACE, 0); }
		public List<TerminalNode> EOLN() { return getTokens(KekParser.EOLN); }
		public TerminalNode EOLN(int i) {
			return getToken(KekParser.EOLN, i);
		}
		public TerminalNode RBRACE() { return getToken(KekParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockContext(ParserRuleContext parent, int invokingState, List<KekVar> additionalVars) {
			super(parent, invokingState);
			this.additionalVars = additionalVars;
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block(List<KekVar> additionalVars) throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState(), additionalVars);
		enterRule(_localctx, 32, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    if (_localctx.additionalVars != null) {
			        KekContext context = contextManager.getContext();
			        _localctx.additionalVars.forEach(context::addAdditionalVar);
			    }

			setState(218);
			match(LBRACE);
			setState(219);
			match(EOLN);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << LPAR) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << FOR) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				{
				setState(220);
				((BlockContext)_localctx).statement = statement();
				((BlockContext)_localctx).statements.add(((BlockContext)_localctx).statement);
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(RBRACE);
			setState(227);
			match(EOLN);

			    ((BlockContext)_localctx).kekBlock =  new KekBlock(
			        ((BlockContext)_localctx).statements.stream().map(x -> x.kekStatement).collect(Collectors.toList()),
			        contextManager.getContext()
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public SimpleStatementContext simpleStatement;
		public FlowStatementContext flowStatement;
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public FlowStatementContext flowStatement() {
			return getRuleContext(FlowStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statement);
		try {
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
			case LPAR:
			case NOT:
			case NOT_SIGN:
			case NOT_SIGN_BITWISE:
			case LBRACKET:
			case INT_LITERAL:
			case BOOL_LITERAL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				((StatementContext)_localctx).simpleStatement = simpleStatement();
				((StatementContext)_localctx).kekStatement =  ((StatementContext)_localctx).simpleStatement.kekStatement;
				}
				break;
			case BREAK:
			case CONTINUE:
			case IF:
			case WHILE:
			case RETURN:
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				((StatementContext)_localctx).flowStatement = flowStatement();
				((StatementContext)_localctx).kekStatement =  ((StatementContext)_localctx).flowStatement.kekStatement;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public IfStatementContext ifStatement;
		public WhileStatementContext whileStatement;
		public ForStatementContext forStatement;
		public ReturnStatementContext returnStatement;
		public BreakStatementContext breakStatement;
		public ContinueStatementContext continueStatement;
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public FlowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flowStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFlowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFlowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFlowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowStatementContext flowStatement() throws RecognitionException {
		FlowStatementContext _localctx = new FlowStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_flowStatement);
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				((FlowStatementContext)_localctx).ifStatement = ifStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).ifStatement.kekIf;
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				((FlowStatementContext)_localctx).whileStatement = whileStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).whileStatement.kekWhile;
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				((FlowStatementContext)_localctx).forStatement = forStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).forStatement.kekFor;
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(247);
				((FlowStatementContext)_localctx).returnStatement = returnStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).returnStatement.kekReturn;
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 5);
				{
				setState(250);
				((FlowStatementContext)_localctx).breakStatement = breakStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).breakStatement.kekStatement;
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 6);
				{
				setState(253);
				((FlowStatementContext)_localctx).continueStatement = continueStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).continueStatement.kekStatement;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public KekFor kekFor;
		public ForArgsDeclContext forArgsDecl;
		public List<ForArgsDeclContext> argsDecl = new ArrayList<ForArgsDeclContext>();
		public AssignStatementNoEolnContext assignStatementNoEoln;
		public List<AssignStatementNoEolnContext> assign = new ArrayList<AssignStatementNoEolnContext>();
		public ExpressionContext expression;
		public List<ExpressionContext> cond = new ArrayList<ExpressionContext>();
		public List<AssignStatementNoEolnContext> afterAssign = new ArrayList<AssignStatementNoEolnContext>();
		public BlockContext block;
		public TerminalNode FOR() { return getToken(KekParser.FOR, 0); }
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(KekParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(KekParser.SEMICOLON, i);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForArgsDeclContext forArgsDecl() {
			return getRuleContext(ForArgsDeclContext.class,0);
		}
		public List<AssignStatementNoEolnContext> assignStatementNoEoln() {
			return getRuleContexts(AssignStatementNoEolnContext.class);
		}
		public AssignStatementNoEolnContext assignStatementNoEoln(int i) {
			return getRuleContext(AssignStatementNoEolnContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(FOR);
			setState(259);
			match(LPAR);
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(260);
				((ForStatementContext)_localctx).forArgsDecl = forArgsDecl();
				((ForStatementContext)_localctx).argsDecl.add(((ForStatementContext)_localctx).forArgsDecl);
				}
				break;
			}
			setState(263);
			match(SEMICOLON);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(264);
				((ForStatementContext)_localctx).assignStatementNoEoln = assignStatementNoEoln();
				((ForStatementContext)_localctx).assign.add(((ForStatementContext)_localctx).assignStatementNoEoln);
				}
			}

			setState(267);
			match(SEMICOLON);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(268);
				((ForStatementContext)_localctx).expression = expression();
				((ForStatementContext)_localctx).cond.add(((ForStatementContext)_localctx).expression);
				}
			}

			setState(271);
			match(SEMICOLON);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(272);
				((ForStatementContext)_localctx).assignStatementNoEoln = assignStatementNoEoln();
				((ForStatementContext)_localctx).afterAssign.add(((ForStatementContext)_localctx).assignStatementNoEoln);
				}
			}

			setState(275);
			match(RPAR);
			setState(276);
			((ForStatementContext)_localctx).block = block(null);

			    ((ForStatementContext)_localctx).kekFor =  new KekFor(
			        ((ForStatementContext)_localctx).argsDecl.stream().map(x -> x.kekVars).findAny().orElse(null),
			        ((ForStatementContext)_localctx).assign.stream().map(x -> x.kekAssign).findAny().orElse(null),
			        ((ForStatementContext)_localctx).cond.stream().map(x -> x.kekExpr).findAny().orElse(null),
			        ((ForStatementContext)_localctx).afterAssign.stream().map(x -> x.kekAssign).findAny().orElse(null),
			        ((ForStatementContext)_localctx).block.kekBlock
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public KekReturn kekReturn;
		public ExpressionContext expression;
		public TerminalNode RETURN() { return getToken(KekParser.RETURN, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(RETURN);
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(280);
				((ReturnStatementContext)_localctx).expression = expression();
				}
			}

			setState(283);
			match(EOLN);
			((ReturnStatementContext)_localctx).kekReturn =  new KekReturn(((ReturnStatementContext)_localctx).expression.kekExpr);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public TerminalNode BREAK() { return getToken(KekParser.BREAK, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(BREAK);
			setState(287);
			match(EOLN);
			((BreakStatementContext)_localctx).kekStatement =  new KekBreak();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public TerminalNode CONTINUE() { return getToken(KekParser.CONTINUE, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(CONTINUE);
			setState(291);
			match(EOLN);
			((ContinueStatementContext)_localctx).kekStatement =  new KekContinue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public KekIf kekIf;
		public IfPositivePartContext ifPositivePart;
		public IfNegativePartContext ifNegativePart;
		public List<IfNegativePartContext> ifNegativeParts = new ArrayList<IfNegativePartContext>();
		public IfPositivePartContext ifPositivePart() {
			return getRuleContext(IfPositivePartContext.class,0);
		}
		public IfNegativePartContext ifNegativePart() {
			return getRuleContext(IfNegativePartContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			((IfStatementContext)_localctx).ifPositivePart = ifPositivePart();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(295);
				((IfStatementContext)_localctx).ifNegativePart = ifNegativePart();
				((IfStatementContext)_localctx).ifNegativeParts.add(((IfStatementContext)_localctx).ifNegativePart);
				}
			}


			    ((IfStatementContext)_localctx).kekIf =  new KekIf(
			        ((IfStatementContext)_localctx).ifPositivePart.kekExpr,
			        ((IfStatementContext)_localctx).ifPositivePart.kekBlock,
			        (((IfStatementContext)_localctx).ifNegativeParts != null && ((IfStatementContext)_localctx).ifNegativeParts.size() == 1 ? ((IfStatementContext)_localctx).ifNegativeParts.get(0).kekBlock : null)
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfPositivePartContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public KekBlock kekBlock;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode IF() { return getToken(KekParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfPositivePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifPositivePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfPositivePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfPositivePart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfPositivePart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfPositivePartContext ifPositivePart() throws RecognitionException {
		IfPositivePartContext _localctx = new IfPositivePartContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ifPositivePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(IF);
			setState(301);
			((IfPositivePartContext)_localctx).expression = expression();
			setState(302);
			((IfPositivePartContext)_localctx).block = block(null);

			    ((IfPositivePartContext)_localctx).kekExpr =  ((IfPositivePartContext)_localctx).expression.kekExpr;
			    ((IfPositivePartContext)_localctx).kekBlock =  ((IfPositivePartContext)_localctx).block.kekBlock;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfNegativePartContext extends ParserRuleContext {
		public KekBlock kekBlock;
		public BlockContext block;
		public TerminalNode ELSE() { return getToken(KekParser.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfNegativePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifNegativePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfNegativePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfNegativePart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfNegativePart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfNegativePartContext ifNegativePart() throws RecognitionException {
		IfNegativePartContext _localctx = new IfNegativePartContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_ifNegativePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(ELSE);
			setState(306);
			((IfNegativePartContext)_localctx).block = block(null);
			((IfNegativePartContext)_localctx).kekBlock =  ((IfNegativePartContext)_localctx).block.kekBlock;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public KekWhile kekWhile;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode WHILE() { return getToken(KekParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(WHILE);
			setState(310);
			((WhileStatementContext)_localctx).expression = expression();
			setState(311);
			((WhileStatementContext)_localctx).block = block(null);
			((WhileStatementContext)_localctx).kekWhile =  new KekWhile(((WhileStatementContext)_localctx).expression.kekExpr, ((WhileStatementContext)_localctx).block.kekBlock);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public AssignStatementContext assignStatement;
		public ExpressionContext expression;
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterSimpleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitSimpleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitSimpleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_simpleStatement);
		try {
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				((SimpleStatementContext)_localctx).assignStatement = assignStatement();
				((SimpleStatementContext)_localctx).kekStatement =  ((SimpleStatementContext)_localctx).assignStatement.kekAssign;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(318);
				((SimpleStatementContext)_localctx).expression = expression();
				setState(319);
				match(EOLN);
				}
				((SimpleStatementContext)_localctx).kekStatement =  KekStatement.of(((SimpleStatementContext)_localctx).expression.kekExpr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStatementContext extends ParserRuleContext {
		public KekAssign kekAssign;
		public VarUsageContext varUsage;
		public List<VarUsageContext> varUsages = new ArrayList<VarUsageContext>();
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode ASSIGN() { return getToken(KekParser.ASSIGN, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public List<VarUsageContext> varUsage() {
			return getRuleContexts(VarUsageContext.class);
		}
		public VarUsageContext varUsage(int i) {
			return getRuleContext(VarUsageContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAssignStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_assignStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			((AssignStatementContext)_localctx).varUsage = varUsage();
			((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(326);
				match(COMMA);
				setState(327);
				((AssignStatementContext)_localctx).varUsage = varUsage();
				((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(ASSIGN);
			setState(334);
			((AssignStatementContext)_localctx).expression = expression();
			((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(335);
				match(COMMA);
				setState(336);
				((AssignStatementContext)_localctx).expression = expression();
				((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
				}
				}
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(342);
			match(EOLN);

			    if (((AssignStatementContext)_localctx).varUsages.size() != ((AssignStatementContext)_localctx).exprs.size()) {
			        throw new RuntimeException("Expected the same count of vars and expressions");
			    }

			    ((AssignStatementContext)_localctx).kekAssign =  new KekAssign(
			        ((AssignStatementContext)_localctx).varUsages.stream().map(x -> x.kekVarUsage).collect(Collectors.toList()),
			        ((AssignStatementContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList()),
			        false
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStatementNoEolnContext extends ParserRuleContext {
		public KekAssign kekAssign;
		public VarUsageContext varUsage;
		public List<VarUsageContext> varUsages = new ArrayList<VarUsageContext>();
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode ASSIGN() { return getToken(KekParser.ASSIGN, 0); }
		public List<VarUsageContext> varUsage() {
			return getRuleContexts(VarUsageContext.class);
		}
		public VarUsageContext varUsage(int i) {
			return getRuleContext(VarUsageContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public AssignStatementNoEolnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatementNoEoln; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAssignStatementNoEoln(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAssignStatementNoEoln(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAssignStatementNoEoln(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatementNoEolnContext assignStatementNoEoln() throws RecognitionException {
		AssignStatementNoEolnContext _localctx = new AssignStatementNoEolnContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_assignStatementNoEoln);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			((AssignStatementNoEolnContext)_localctx).varUsage = varUsage();
			((AssignStatementNoEolnContext)_localctx).varUsages.add(((AssignStatementNoEolnContext)_localctx).varUsage);
			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(346);
				match(COMMA);
				setState(347);
				((AssignStatementNoEolnContext)_localctx).varUsage = varUsage();
				((AssignStatementNoEolnContext)_localctx).varUsages.add(((AssignStatementNoEolnContext)_localctx).varUsage);
				}
				}
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(353);
			match(ASSIGN);
			setState(354);
			((AssignStatementNoEolnContext)_localctx).expression = expression();
			((AssignStatementNoEolnContext)_localctx).exprs.add(((AssignStatementNoEolnContext)_localctx).expression);
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(355);
				match(COMMA);
				setState(356);
				((AssignStatementNoEolnContext)_localctx).expression = expression();
				((AssignStatementNoEolnContext)_localctx).exprs.add(((AssignStatementNoEolnContext)_localctx).expression);
				}
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    if (((AssignStatementNoEolnContext)_localctx).varUsages.size() != ((AssignStatementNoEolnContext)_localctx).exprs.size()) {
			        throw new RuntimeException("Expected the same count of vars and expressions");
			    }

			    ((AssignStatementNoEolnContext)_localctx).kekAssign =  new KekAssign(
			        ((AssignStatementNoEolnContext)_localctx).varUsages.stream().map(x -> x.kekVarUsage).collect(Collectors.toList()),
			        ((AssignStatementNoEolnContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList()),
			        true
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public String name;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(ID);
			((VarContext)_localctx).name =  _localctx.getChild(0).getText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarUsageContext extends ParserRuleContext {
		public KekVarUsage kekVarUsage;
		public VarContext var;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public VarUsageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varUsage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarUsage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarUsage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarUsage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarUsageContext varUsage() throws RecognitionException {
		VarUsageContext _localctx = new VarUsageContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_varUsage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			((VarUsageContext)_localctx).var = var();

			    KekVar kekVar = contextManager.getVar(((VarUsageContext)_localctx).var.name);
			    ((VarUsageContext)_localctx).kekVarUsage =  new KekVarUsage(kekVar);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public OperatorContext operator;
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			((ExpressionContext)_localctx).operator = operator();
			((ExpressionContext)_localctx).kekExpr =  ((ExpressionContext)_localctx).operator.kekExpr;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public OrOperatorContext orOperator;
		public OrOperatorContext orOperator() {
			return getRuleContext(OrOperatorContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			((OperatorContext)_localctx).orOperator = orOperator();
			((OperatorContext)_localctx).kekExpr =  ((OperatorContext)_localctx).orOperator.kekExpr;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public AndOperatorContext andOperator;
		public List<AndOperatorContext> lst = new ArrayList<AndOperatorContext>();
		public OrContext or;
		public List<OrContext> rules = new ArrayList<OrContext>();
		public List<AndOperatorContext> andOperator() {
			return getRuleContexts(AndOperatorContext.class);
		}
		public AndOperatorContext andOperator(int i) {
			return getRuleContext(AndOperatorContext.class,i);
		}
		public List<OrContext> or() {
			return getRuleContexts(OrContext.class);
		}
		public OrContext or(int i) {
			return getRuleContext(OrContext.class,i);
		}
		public OrOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOrOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOrOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOrOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrOperatorContext orOperator() throws RecognitionException {
		OrOperatorContext _localctx = new OrOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_orOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			((OrOperatorContext)_localctx).andOperator = andOperator();
			((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
			setState(382);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(377);
					((OrOperatorContext)_localctx).or = or();
					((OrOperatorContext)_localctx).rules.add(((OrOperatorContext)_localctx).or);
					setState(378);
					((OrOperatorContext)_localctx).andOperator = andOperator();
					((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
					}
					} 
				}
				setState(384);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}

			    ((OrOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((OrOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((OrOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode OR() { return getToken(KekParser.OR, 0); }
		public TerminalNode OR_SIGN() { return getToken(KekParser.OR_SIGN, 0); }
		public TerminalNode OR_SIGN_BITWISE() { return getToken(KekParser.OR_SIGN_BITWISE, 0); }
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_or);
		try {
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				match(OR);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				match(OR_SIGN);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				match(OR_SIGN_BITWISE);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public EqualsOperatorContext equalsOperator;
		public List<EqualsOperatorContext> lst = new ArrayList<EqualsOperatorContext>();
		public AndContext and;
		public List<AndContext> rules = new ArrayList<AndContext>();
		public List<EqualsOperatorContext> equalsOperator() {
			return getRuleContexts(EqualsOperatorContext.class);
		}
		public EqualsOperatorContext equalsOperator(int i) {
			return getRuleContext(EqualsOperatorContext.class,i);
		}
		public List<AndContext> and() {
			return getRuleContexts(AndContext.class);
		}
		public AndContext and(int i) {
			return getRuleContext(AndContext.class,i);
		}
		public AndOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAndOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAndOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAndOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndOperatorContext andOperator() throws RecognitionException {
		AndOperatorContext _localctx = new AndOperatorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_andOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
			((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
			setState(401);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(396);
					((AndOperatorContext)_localctx).and = and();
					((AndOperatorContext)_localctx).rules.add(((AndOperatorContext)_localctx).and);
					setState(397);
					((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
					((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
					}
					} 
				}
				setState(403);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}

			    ((AndOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((AndOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((AndOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode AND() { return getToken(KekParser.AND, 0); }
		public TerminalNode AND_SIGN() { return getToken(KekParser.AND_SIGN, 0); }
		public TerminalNode AND_SIGN_BITWISE() { return getToken(KekParser.AND_SIGN_BITWISE, 0); }
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_and);
		try {
			setState(412);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(AND);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				match(AND_SIGN);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(410);
				match(AND_SIGN_BITWISE);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualsOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public PlusOperatorContext plusOperator;
		public List<PlusOperatorContext> lst = new ArrayList<PlusOperatorContext>();
		public EqualsContext equals;
		public List<EqualsContext> rules = new ArrayList<EqualsContext>();
		public List<PlusOperatorContext> plusOperator() {
			return getRuleContexts(PlusOperatorContext.class);
		}
		public PlusOperatorContext plusOperator(int i) {
			return getRuleContext(PlusOperatorContext.class,i);
		}
		public List<EqualsContext> equals() {
			return getRuleContexts(EqualsContext.class);
		}
		public EqualsContext equals(int i) {
			return getRuleContext(EqualsContext.class,i);
		}
		public EqualsOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalsOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterEqualsOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitEqualsOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitEqualsOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualsOperatorContext equalsOperator() throws RecognitionException {
		EqualsOperatorContext _localctx = new EqualsOperatorContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_equalsOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
			((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
			setState(420);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(415);
					((EqualsOperatorContext)_localctx).equals = equals();
					((EqualsOperatorContext)_localctx).rules.add(((EqualsOperatorContext)_localctx).equals);
					setState(416);
					((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
					((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
					}
					} 
				}
				setState(422);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}

			    ((EqualsOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((EqualsOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((EqualsOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualsContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode EQUALS() { return getToken(KekParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(KekParser.NOT_EQUALS, 0); }
		public EqualsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualsContext equals() throws RecognitionException {
		EqualsContext _localctx = new EqualsContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_equals);
		try {
			setState(429);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				match(EQUALS);
				((EqualsContext)_localctx).kekOperatorRule =  KekOperatorRule.EQUALS;
				}
				break;
			case NOT_EQUALS:
				enterOuterAlt(_localctx, 2);
				{
				setState(427);
				match(NOT_EQUALS);
				((EqualsContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT_EQUALS;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public MulOperatorContext mulOperator;
		public List<MulOperatorContext> lst = new ArrayList<MulOperatorContext>();
		public PlusContext plus;
		public List<PlusContext> rules = new ArrayList<PlusContext>();
		public List<MulOperatorContext> mulOperator() {
			return getRuleContexts(MulOperatorContext.class);
		}
		public MulOperatorContext mulOperator(int i) {
			return getRuleContext(MulOperatorContext.class,i);
		}
		public List<PlusContext> plus() {
			return getRuleContexts(PlusContext.class);
		}
		public PlusContext plus(int i) {
			return getRuleContext(PlusContext.class,i);
		}
		public PlusOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPlusOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPlusOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPlusOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusOperatorContext plusOperator() throws RecognitionException {
		PlusOperatorContext _localctx = new PlusOperatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_plusOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			((PlusOperatorContext)_localctx).mulOperator = mulOperator();
			((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
			setState(437);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(432);
					((PlusOperatorContext)_localctx).plus = plus();
					((PlusOperatorContext)_localctx).rules.add(((PlusOperatorContext)_localctx).plus);
					setState(433);
					((PlusOperatorContext)_localctx).mulOperator = mulOperator();
					((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
					}
					} 
				}
				setState(439);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}

			    ((PlusOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((PlusOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((PlusOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode PLUS_SIGN() { return getToken(KekParser.PLUS_SIGN, 0); }
		public TerminalNode MINUS_SIGN() { return getToken(KekParser.MINUS_SIGN, 0); }
		public PlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusContext plus() throws RecognitionException {
		PlusContext _localctx = new PlusContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_plus);
		try {
			setState(446);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(442);
				match(PLUS_SIGN);
				((PlusContext)_localctx).kekOperatorRule =  KekOperatorRule.PLUS;
				}
				break;
			case MINUS_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(444);
				match(MINUS_SIGN);
				((PlusContext)_localctx).kekOperatorRule =  KekOperatorRule.MINUS;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public IndexOperatorContext indexOperator;
		public List<IndexOperatorContext> lst = new ArrayList<IndexOperatorContext>();
		public MulContext mul;
		public List<MulContext> rules = new ArrayList<MulContext>();
		public List<IndexOperatorContext> indexOperator() {
			return getRuleContexts(IndexOperatorContext.class);
		}
		public IndexOperatorContext indexOperator(int i) {
			return getRuleContext(IndexOperatorContext.class,i);
		}
		public List<MulContext> mul() {
			return getRuleContexts(MulContext.class);
		}
		public MulContext mul(int i) {
			return getRuleContext(MulContext.class,i);
		}
		public MulOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMulOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMulOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMulOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulOperatorContext mulOperator() throws RecognitionException {
		MulOperatorContext _localctx = new MulOperatorContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_mulOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			((MulOperatorContext)_localctx).indexOperator = indexOperator();
			((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).indexOperator);
			setState(454);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(449);
					((MulOperatorContext)_localctx).mul = mul();
					((MulOperatorContext)_localctx).rules.add(((MulOperatorContext)_localctx).mul);
					setState(450);
					((MulOperatorContext)_localctx).indexOperator = indexOperator();
					((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).indexOperator);
					}
					} 
				}
				setState(456);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}

			    ((MulOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((MulOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((MulOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode MUL_SIGN() { return getToken(KekParser.MUL_SIGN, 0); }
		public TerminalNode DIV() { return getToken(KekParser.DIV, 0); }
		public TerminalNode DIV_SIGN() { return getToken(KekParser.DIV_SIGN, 0); }
		public TerminalNode MOD() { return getToken(KekParser.MOD, 0); }
		public TerminalNode MOD_SIGN() { return getToken(KekParser.MOD_SIGN, 0); }
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMul(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		MulContext _localctx = new MulContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_mul);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MUL_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(459);
				match(MUL_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MUL;
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(461);
				match(DIV);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case DIV_SIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(463);
				match(DIV_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case MOD:
				enterOuterAlt(_localctx, 4);
				{
				setState(465);
				match(MOD);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MOD;
				}
				break;
			case MOD_SIGN:
				enterOuterAlt(_localctx, 5);
				{
				setState(467);
				match(MOD_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MOD;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public NotOperatorContext notOperator;
		public List<NotOperatorContext> lst = new ArrayList<NotOperatorContext>();
		public IndexContext index;
		public List<IndexContext> rules = new ArrayList<IndexContext>();
		public List<NotOperatorContext> notOperator() {
			return getRuleContexts(NotOperatorContext.class);
		}
		public NotOperatorContext notOperator(int i) {
			return getRuleContext(NotOperatorContext.class,i);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public IndexOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIndexOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIndexOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIndexOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexOperatorContext indexOperator() throws RecognitionException {
		IndexOperatorContext _localctx = new IndexOperatorContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_indexOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			((IndexOperatorContext)_localctx).notOperator = notOperator();
			((IndexOperatorContext)_localctx).lst.add(((IndexOperatorContext)_localctx).notOperator);
			setState(477);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(472);
					((IndexOperatorContext)_localctx).index = index();
					((IndexOperatorContext)_localctx).rules.add(((IndexOperatorContext)_localctx).index);
					setState(473);
					((IndexOperatorContext)_localctx).notOperator = notOperator();
					((IndexOperatorContext)_localctx).lst.add(((IndexOperatorContext)_localctx).notOperator);
					}
					} 
				}
				setState(479);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}

			    ((IndexOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((IndexOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((IndexOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode INDEX() { return getToken(KekParser.INDEX, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			match(INDEX);
			((IndexContext)_localctx).kekOperatorRule =  KekOperatorRule.INDEX;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public ExpressionContext expression;
		public NotContext not;
		public FuncCallContext funcCall;
		public VarUsageContext varUsage;
		public LiteralContext literal;
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public VarUsageContext varUsage() {
			return getRuleContext(VarUsageContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public NotOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNotOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNotOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNotOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotOperatorContext notOperator() throws RecognitionException {
		NotOperatorContext _localctx = new NotOperatorContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_notOperator);
		try {
			setState(505);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(485);
				match(LPAR);
				setState(486);
				((NotOperatorContext)_localctx).expression = expression();
				setState(487);
				match(RPAR);
				}
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).expression.kekExpr;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(491);
				((NotOperatorContext)_localctx).not = not();
				setState(492);
				((NotOperatorContext)_localctx).expression = expression();
				}
				((NotOperatorContext)_localctx).kekExpr =  new KekOperator(((NotOperatorContext)_localctx).not.kekOperatorRule, ((NotOperatorContext)_localctx).expression.kekExpr);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(496);
				((NotOperatorContext)_localctx).funcCall = funcCall();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).funcCall.kekFuncCall;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(499);
				((NotOperatorContext)_localctx).varUsage = varUsage();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).varUsage.kekVarUsage;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(502);
				((NotOperatorContext)_localctx).literal = literal();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).literal.kekExpr;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode NOT() { return getToken(KekParser.NOT, 0); }
		public TerminalNode NOT_SIGN() { return getToken(KekParser.NOT_SIGN, 0); }
		public TerminalNode NOT_SIGN_BITWISE() { return getToken(KekParser.NOT_SIGN_BITWISE, 0); }
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_not);
		try {
			setState(513);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(507);
				match(NOT);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(509);
				match(NOT_SIGN);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(511);
				match(NOT_SIGN_BITWISE);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncCallContext extends ParserRuleContext {
		public KekFuncCall kekFuncCall;
		public FuncNameContext funcName;
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			((FuncCallContext)_localctx).funcName = funcName();
			setState(516);
			match(LPAR);
			setState(525);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(517);
				((FuncCallContext)_localctx).expression = expression();
				((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
				setState(522);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(518);
					match(COMMA);
					setState(519);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(524);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(527);
			match(RPAR);

			    KekFunc kekFunc = contextManager.getFunc(((FuncCallContext)_localctx).funcName.name);
			    ((FuncCallContext)_localctx).kekFuncCall =  new KekFuncCall(
			        kekFunc,
			        ((FuncCallContext)_localctx).args.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public IntLiteralContext intLiteral;
		public BoolLiteralContext boolLiteral;
		public ArrayLiteralContext arrayLiteral;
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_literal);
		try {
			setState(539);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(530);
				((LiteralContext)_localctx).intLiteral = intLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).intLiteral.kekLiteral;
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(533);
				((LiteralContext)_localctx).boolLiteral = boolLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).boolLiteral.kekLiteral;
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(536);
				((LiteralContext)_localctx).arrayLiteral = arrayLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).arrayLiteral.kekExpr;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode LBRACKET() { return getToken(KekParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(KekParser.RBRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			match(LBRACKET);
			setState(542);
			((ArrayLiteralContext)_localctx).expression = expression();
			((ArrayLiteralContext)_localctx).exprs.add(((ArrayLiteralContext)_localctx).expression);
			setState(547);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(543);
				match(COMMA);
				setState(544);
				((ArrayLiteralContext)_localctx).expression = expression();
				((ArrayLiteralContext)_localctx).exprs.add(((ArrayLiteralContext)_localctx).expression);
				}
				}
				setState(549);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(550);
			match(RBRACKET);

			    List<KekExpr> args = ((ArrayLiteralContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList());
			    KekType expectedType = args.get(0).getType();
			    if (!args.stream().map(KekExpr::getType).allMatch(expectedType::equals))
			        throw new RuntimeException();

			    ((ArrayLiteralContext)_localctx).kekExpr =  KekArrayLiteral.get(contextManager.getGlobalContext(), expectedType, args);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntLiteralContext extends ParserRuleContext {
		public KekLiteral kekLiteral;
		public TerminalNode INT_LITERAL() { return getToken(KekParser.INT_LITERAL, 0); }
		public IntLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiteralContext intLiteral() throws RecognitionException {
		IntLiteralContext _localctx = new IntLiteralContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(553);
			match(INT_LITERAL);

			    ((IntLiteralContext)_localctx).kekLiteral =  new KekLiteral(KekPrimitiveType.INT, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralContext extends ParserRuleContext {
		public KekLiteral kekLiteral;
		public TerminalNode BOOL_LITERAL() { return getToken(KekParser.BOOL_LITERAL, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			match(BOOL_LITERAL);

			    ((BoolLiteralContext)_localctx).kekLiteral =  new KekLiteral(KekPrimitiveType.BOOL, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u0232\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\7\2q\n\2\f\2\16\2t\13\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6\u008c\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b\u0097"+
		"\n\b\3\t\3\t\5\t\u009b\n\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u00a9\n\13\3\13\3\13\5\13\u00ad\n\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\7\r\u00b7\n\r\f\r\16\r\u00ba\13\r\5\r\u00bc\n\r\3\r\3"+
		"\r\3\16\3\16\3\16\7\16\u00c3\n\16\f\16\16\16\u00c6\13\16\5\16\u00c8\n"+
		"\16\3\16\3\16\3\17\6\17\u00cd\n\17\r\17\16\17\u00ce\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u00d8\n\20\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00e0"+
		"\n\22\f\22\16\22\u00e3\13\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\5\23\u00ef\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0103\n\24\3\25\3\25"+
		"\3\25\5\25\u0108\n\25\3\25\3\25\5\25\u010c\n\25\3\25\3\25\5\25\u0110\n"+
		"\25\3\25\3\25\5\25\u0114\n\25\3\25\3\25\3\25\3\25\3\26\3\26\5\26\u011c"+
		"\n\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\5\31\u012b\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\5\35\u0146\n\35\3\36\3\36\3\36\7\36\u014b\n\36\f\36\16\36\u014e\13\36"+
		"\3\36\3\36\3\36\3\36\7\36\u0154\n\36\f\36\16\36\u0157\13\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\7\37\u015f\n\37\f\37\16\37\u0162\13\37\3\37\3\37"+
		"\3\37\3\37\7\37\u0168\n\37\f\37\16\37\u016b\13\37\3\37\3\37\3 \3 \3 \3"+
		"!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\7$\u017f\n$\f$\16$\u0182\13$"+
		"\3$\3$\3%\3%\3%\3%\3%\3%\5%\u018c\n%\3&\3&\3&\3&\7&\u0192\n&\f&\16&\u0195"+
		"\13&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u019f\n\'\3(\3(\3(\3(\7(\u01a5"+
		"\n(\f(\16(\u01a8\13(\3(\3(\3)\3)\3)\3)\5)\u01b0\n)\3*\3*\3*\3*\7*\u01b6"+
		"\n*\f*\16*\u01b9\13*\3*\3*\3+\3+\3+\3+\5+\u01c1\n+\3,\3,\3,\3,\7,\u01c7"+
		"\n,\f,\16,\u01ca\13,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u01d8\n-\3"+
		".\3.\3.\3.\7.\u01de\n.\f.\16.\u01e1\13.\3.\3.\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\5\60\u01fc\n\60\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0204"+
		"\n\61\3\62\3\62\3\62\3\62\3\62\7\62\u020b\n\62\f\62\16\62\u020e\13\62"+
		"\5\62\u0210\n\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\5\63\u021e\n\63\3\64\3\64\3\64\3\64\7\64\u0224\n\64\f\64\16\64\u0227"+
		"\13\64\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\66\2\2\67\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\\^`bdfhj\2\2\2\u0238\2l\3\2\2\2\4x\3\2\2\2\6|\3\2\2\2\b\u0081\3\2"+
		"\2\2\n\u008b\3\2\2\2\f\u008d\3\2\2\2\16\u0096\3\2\2\2\20\u0098\3\2\2\2"+
		"\22\u009f\3\2\2\2\24\u00a1\3\2\2\2\26\u00b0\3\2\2\2\30\u00bb\3\2\2\2\32"+
		"\u00c7\3\2\2\2\34\u00cc\3\2\2\2\36\u00d7\3\2\2\2 \u00d9\3\2\2\2\"\u00db"+
		"\3\2\2\2$\u00ee\3\2\2\2&\u0102\3\2\2\2(\u0104\3\2\2\2*\u0119\3\2\2\2,"+
		"\u0120\3\2\2\2.\u0124\3\2\2\2\60\u0128\3\2\2\2\62\u012e\3\2\2\2\64\u0133"+
		"\3\2\2\2\66\u0137\3\2\2\28\u0145\3\2\2\2:\u0147\3\2\2\2<\u015b\3\2\2\2"+
		">\u016e\3\2\2\2@\u0171\3\2\2\2B\u0174\3\2\2\2D\u0177\3\2\2\2F\u017a\3"+
		"\2\2\2H\u018b\3\2\2\2J\u018d\3\2\2\2L\u019e\3\2\2\2N\u01a0\3\2\2\2P\u01af"+
		"\3\2\2\2R\u01b1\3\2\2\2T\u01c0\3\2\2\2V\u01c2\3\2\2\2X\u01d7\3\2\2\2Z"+
		"\u01d9\3\2\2\2\\\u01e4\3\2\2\2^\u01fb\3\2\2\2`\u0203\3\2\2\2b\u0205\3"+
		"\2\2\2d\u021d\3\2\2\2f\u021f\3\2\2\2h\u022b\3\2\2\2j\u022e\3\2\2\2lr\b"+
		"\2\1\2mq\5\20\t\2nq\5\6\4\2oq\5\24\13\2pm\3\2\2\2pn\3\2\2\2po\3\2\2\2"+
		"qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7\2\2\3vw\b\2\1\2"+
		"w\3\3\2\2\2xy\5> \2yz\5\b\5\2z{\b\3\1\2{\5\3\2\2\2|}\7\3\2\2}~\5\4\3\2"+
		"~\177\7\63\2\2\177\u0080\b\4\1\2\u0080\7\3\2\2\2\u0081\u0082\7\4\2\2\u0082"+
		"\u0083\5\n\6\2\u0083\u0084\b\5\1\2\u0084\t\3\2\2\2\u0085\u0086\5\16\b"+
		"\2\u0086\u0087\b\6\1\2\u0087\u008c\3\2\2\2\u0088\u0089\5\f\7\2\u0089\u008a"+
		"\b\6\1\2\u008a\u008c\3\2\2\2\u008b\u0085\3\2\2\2\u008b\u0088\3\2\2\2\u008c"+
		"\13\3\2\2\2\u008d\u008e\7,\2\2\u008e\u008f\5\n\6\2\u008f\u0090\7-\2\2"+
		"\u0090\u0091\b\7\1\2\u0091\r\3\2\2\2\u0092\u0093\7\6\2\2\u0093\u0097\b"+
		"\b\1\2\u0094\u0095\7\7\2\2\u0095\u0097\b\b\1\2\u0096\u0092\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0097\17\3\2\2\2\u0098\u009a\7\n\2\2\u0099\u009b\7\31\2"+
		"\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d"+
		"\5\22\n\2\u009d\u009e\7\63\2\2\u009e\21\3\2\2\2\u009f\u00a0\7\62\2\2\u00a0"+
		"\23\3\2\2\2\u00a1\u00a2\7\13\2\2\u00a2\u00a3\5\26\f\2\u00a3\u00a4\7\f"+
		"\2\2\u00a4\u00a5\5\30\r\2\u00a5\u00a6\7\r\2\2\u00a6\u00a8\5\b\5\2\u00a7"+
		"\u00a9\5\34\17\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ac\3"+
		"\2\2\2\u00aa\u00ad\5\"\22\2\u00ab\u00ad\7\63\2\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\b\13\1\2\u00af\25\3\2\2"+
		"\2\u00b0\u00b1\7\62\2\2\u00b1\u00b2\b\f\1\2\u00b2\27\3\2\2\2\u00b3\u00b8"+
		"\5\4\3\2\u00b4\u00b5\7\16\2\2\u00b5\u00b7\5\4\3\2\u00b6\u00b4\3\2\2\2"+
		"\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc"+
		"\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00be\b\r\1\2\u00be\31\3\2\2\2\u00bf\u00c4\5\4\3"+
		"\2\u00c0\u00c1\7\16\2\2\u00c1\u00c3\5\4\3\2\u00c2\u00c0\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c8\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00bf\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00ca\b\16\1\2\u00ca\33\3\2\2\2\u00cb\u00cd\5\36"+
		"\20\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\17\1\2\u00d1\35\3\2\2"+
		"\2\u00d2\u00d3\7\17\2\2\u00d3\u00d8\b\20\1\2\u00d4\u00d5\5 \21\2\u00d5"+
		"\u00d6\b\20\1\2\u00d6\u00d8\3\2\2\2\u00d7\u00d2\3\2\2\2\u00d7\u00d4\3"+
		"\2\2\2\u00d8\37\3\2\2\2\u00d9\u00da\7\31\2\2\u00da!\3\2\2\2\u00db\u00dc"+
		"\b\22\1\2\u00dc\u00dd\7\20\2\2\u00dd\u00e1\7\63\2\2\u00de\u00e0\5$\23"+
		"\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2"+
		"\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\21\2\2"+
		"\u00e5\u00e6\7\63\2\2\u00e6\u00e7\b\22\1\2\u00e7#\3\2\2\2\u00e8\u00e9"+
		"\58\35\2\u00e9\u00ea\b\23\1\2\u00ea\u00ef\3\2\2\2\u00eb\u00ec\5&\24\2"+
		"\u00ec\u00ed\b\23\1\2\u00ed\u00ef\3\2\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00eb"+
		"\3\2\2\2\u00ef%\3\2\2\2\u00f0\u00f1\5\60\31\2\u00f1\u00f2\b\24\1\2\u00f2"+
		"\u0103\3\2\2\2\u00f3\u00f4\5\66\34\2\u00f4\u00f5\b\24\1\2\u00f5\u0103"+
		"\3\2\2\2\u00f6\u00f7\5(\25\2\u00f7\u00f8\b\24\1\2\u00f8\u0103\3\2\2\2"+
		"\u00f9\u00fa\5*\26\2\u00fa\u00fb\b\24\1\2\u00fb\u0103\3\2\2\2\u00fc\u00fd"+
		"\5,\27\2\u00fd\u00fe\b\24\1\2\u00fe\u0103\3\2\2\2\u00ff\u0100\5.\30\2"+
		"\u0100\u0101\b\24\1\2\u0101\u0103\3\2\2\2\u0102\u00f0\3\2\2\2\u0102\u00f3"+
		"\3\2\2\2\u0102\u00f6\3\2\2\2\u0102\u00f9\3\2\2\2\u0102\u00fc\3\2\2\2\u0102"+
		"\u00ff\3\2\2\2\u0103\'\3\2\2\2\u0104\u0105\7/\2\2\u0105\u0107\7\f\2\2"+
		"\u0106\u0108\5\32\16\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109"+
		"\3\2\2\2\u0109\u010b\7\5\2\2\u010a\u010c\5<\37\2\u010b\u010a\3\2\2\2\u010b"+
		"\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\7\5\2\2\u010e\u0110\5B"+
		"\"\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0113\7\5\2\2\u0112\u0114\5<\37\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\u0115\3\2\2\2\u0115\u0116\7\r\2\2\u0116\u0117\5\"\22\2\u0117"+
		"\u0118\b\25\1\2\u0118)\3\2\2\2\u0119\u011b\7\30\2\2\u011a\u011c\5B\"\2"+
		"\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e"+
		"\7\63\2\2\u011e\u011f\b\26\1\2\u011f+\3\2\2\2\u0120\u0121\7\22\2\2\u0121"+
		"\u0122\7\63\2\2\u0122\u0123\b\27\1\2\u0123-\3\2\2\2\u0124\u0125\7\23\2"+
		"\2\u0125\u0126\7\63\2\2\u0126\u0127\b\30\1\2\u0127/\3\2\2\2\u0128\u012a"+
		"\5\62\32\2\u0129\u012b\5\64\33\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2"+
		"\2\u012b\u012c\3\2\2\2\u012c\u012d\b\31\1\2\u012d\61\3\2\2\2\u012e\u012f"+
		"\7\24\2\2\u012f\u0130\5B\"\2\u0130\u0131\5\"\22\2\u0131\u0132\b\32\1\2"+
		"\u0132\63\3\2\2\2\u0133\u0134\7\25\2\2\u0134\u0135\5\"\22\2\u0135\u0136"+
		"\b\33\1\2\u0136\65\3\2\2\2\u0137\u0138\7\26\2\2\u0138\u0139\5B\"\2\u0139"+
		"\u013a\5\"\22\2\u013a\u013b\b\34\1\2\u013b\67\3\2\2\2\u013c\u0146\5\6"+
		"\4\2\u013d\u013e\5:\36\2\u013e\u013f\b\35\1\2\u013f\u0146\3\2\2\2\u0140"+
		"\u0141\5B\"\2\u0141\u0142\7\63\2\2\u0142\u0143\3\2\2\2\u0143\u0144\b\35"+
		"\1\2\u0144\u0146\3\2\2\2\u0145\u013c\3\2\2\2\u0145\u013d\3\2\2\2\u0145"+
		"\u0140\3\2\2\2\u01469\3\2\2\2\u0147\u014c\5@!\2\u0148\u0149\7\16\2\2\u0149"+
		"\u014b\5@!\2\u014a\u0148\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2"+
		"\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150"+
		"\7\27\2\2\u0150\u0155\5B\"\2\u0151\u0152\7\16\2\2\u0152\u0154\5B\"\2\u0153"+
		"\u0151\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2"+
		"\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0159\7\63\2\2\u0159"+
		"\u015a\b\36\1\2\u015a;\3\2\2\2\u015b\u0160\5@!\2\u015c\u015d\7\16\2\2"+
		"\u015d\u015f\5@!\2\u015e\u015c\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163"+
		"\u0164\7\27\2\2\u0164\u0169\5B\"\2\u0165\u0166\7\16\2\2\u0166\u0168\5"+
		"B\"\2\u0167\u0165\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169"+
		"\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u016d\b\37"+
		"\1\2\u016d=\3\2\2\2\u016e\u016f\7\62\2\2\u016f\u0170\b \1\2\u0170?\3\2"+
		"\2\2\u0171\u0172\5> \2\u0172\u0173\b!\1\2\u0173A\3\2\2\2\u0174\u0175\5"+
		"D#\2\u0175\u0176\b\"\1\2\u0176C\3\2\2\2\u0177\u0178\5F$\2\u0178\u0179"+
		"\b#\1\2\u0179E\3\2\2\2\u017a\u0180\5J&\2\u017b\u017c\5H%\2\u017c\u017d"+
		"\5J&\2\u017d\u017f\3\2\2\2\u017e\u017b\3\2\2\2\u017f\u0182\3\2\2\2\u0180"+
		"\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0183\3\2\2\2\u0182\u0180\3\2"+
		"\2\2\u0183\u0184\b$\1\2\u0184G\3\2\2\2\u0185\u0186\7\32\2\2\u0186\u018c"+
		"\b%\1\2\u0187\u0188\7\34\2\2\u0188\u018c\b%\1\2\u0189\u018a\7\33\2\2\u018a"+
		"\u018c\b%\1\2\u018b\u0185\3\2\2\2\u018b\u0187\3\2\2\2\u018b\u0189\3\2"+
		"\2\2\u018cI\3\2\2\2\u018d\u0193\5N(\2\u018e\u018f\5L\'\2\u018f\u0190\5"+
		"N(\2\u0190\u0192\3\2\2\2\u0191\u018e\3\2\2\2\u0192\u0195\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195\u0193\3\2"+
		"\2\2\u0196\u0197\b&\1\2\u0197K\3\2\2\2\u0198\u0199\7\35\2\2\u0199\u019f"+
		"\b\'\1\2\u019a\u019b\7\37\2\2\u019b\u019f\b\'\1\2\u019c\u019d\7\36\2\2"+
		"\u019d\u019f\b\'\1\2\u019e\u0198\3\2\2\2\u019e\u019a\3\2\2\2\u019e\u019c"+
		"\3\2\2\2\u019fM\3\2\2\2\u01a0\u01a6\5R*\2\u01a1\u01a2\5P)\2\u01a2\u01a3"+
		"\5R*\2\u01a3\u01a5\3\2\2\2\u01a4\u01a1\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6"+
		"\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a6\3\2"+
		"\2\2\u01a9\u01aa\b(\1\2\u01aaO\3\2\2\2\u01ab\u01ac\7*\2\2\u01ac\u01b0"+
		"\b)\1\2\u01ad\u01ae\7+\2\2\u01ae\u01b0\b)\1\2\u01af\u01ab\3\2\2\2\u01af"+
		"\u01ad\3\2\2\2\u01b0Q\3\2\2\2\u01b1\u01b7\5V,\2\u01b2\u01b3\5T+\2\u01b3"+
		"\u01b4\5V,\2\u01b4\u01b6\3\2\2\2\u01b5\u01b2\3\2\2\2\u01b6\u01b9\3\2\2"+
		"\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01b7"+
		"\3\2\2\2\u01ba\u01bb\b*\1\2\u01bbS\3\2\2\2\u01bc\u01bd\7#\2\2\u01bd\u01c1"+
		"\b+\1\2\u01be\u01bf\7$\2\2\u01bf\u01c1\b+\1\2\u01c0\u01bc\3\2\2\2\u01c0"+
		"\u01be\3\2\2\2\u01c1U\3\2\2\2\u01c2\u01c8\5Z.\2\u01c3\u01c4\5X-\2\u01c4"+
		"\u01c5\5Z.\2\u01c5\u01c7\3\2\2\2\u01c6\u01c3\3\2\2\2\u01c7\u01ca\3\2\2"+
		"\2\u01c8\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca\u01c8"+
		"\3\2\2\2\u01cb\u01cc\b,\1\2\u01ccW\3\2\2\2\u01cd\u01ce\7%\2\2\u01ce\u01d8"+
		"\b-\1\2\u01cf\u01d0\7&\2\2\u01d0\u01d8\b-\1\2\u01d1\u01d2\7\'\2\2\u01d2"+
		"\u01d8\b-\1\2\u01d3\u01d4\7(\2\2\u01d4\u01d8\b-\1\2\u01d5\u01d6\7)\2\2"+
		"\u01d6\u01d8\b-\1\2\u01d7\u01cd\3\2\2\2\u01d7\u01cf\3\2\2\2\u01d7\u01d1"+
		"\3\2\2\2\u01d7\u01d3\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d8Y\3\2\2\2\u01d9"+
		"\u01df\5^\60\2\u01da\u01db\5\\/\2\u01db\u01dc\5^\60\2\u01dc\u01de\3\2"+
		"\2\2\u01dd\u01da\3\2\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2\2\2\u01df"+
		"\u01e0\3\2\2\2\u01e0\u01e2\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01e3\b."+
		"\1\2\u01e3[\3\2\2\2\u01e4\u01e5\7.\2\2\u01e5\u01e6\b/\1\2\u01e6]\3\2\2"+
		"\2\u01e7\u01e8\7\f\2\2\u01e8\u01e9\5B\"\2\u01e9\u01ea\7\r\2\2\u01ea\u01eb"+
		"\3\2\2\2\u01eb\u01ec\b\60\1\2\u01ec\u01fc\3\2\2\2\u01ed\u01ee\5`\61\2"+
		"\u01ee\u01ef\5B\"\2\u01ef\u01f0\3\2\2\2\u01f0\u01f1\b\60\1\2\u01f1\u01fc"+
		"\3\2\2\2\u01f2\u01f3\5b\62\2\u01f3\u01f4\b\60\1\2\u01f4\u01fc\3\2\2\2"+
		"\u01f5\u01f6\5@!\2\u01f6\u01f7\b\60\1\2\u01f7\u01fc\3\2\2\2\u01f8\u01f9"+
		"\5d\63\2\u01f9\u01fa\b\60\1\2\u01fa\u01fc\3\2\2\2\u01fb\u01e7\3\2\2\2"+
		"\u01fb\u01ed\3\2\2\2\u01fb\u01f2\3\2\2\2\u01fb\u01f5\3\2\2\2\u01fb\u01f8"+
		"\3\2\2\2\u01fc_\3\2\2\2\u01fd\u01fe\7 \2\2\u01fe\u0204\b\61\1\2\u01ff"+
		"\u0200\7!\2\2\u0200\u0204\b\61\1\2\u0201\u0202\7\"\2\2\u0202\u0204\b\61"+
		"\1\2\u0203\u01fd\3\2\2\2\u0203\u01ff\3\2\2\2\u0203\u0201\3\2\2\2\u0204"+
		"a\3\2\2\2\u0205\u0206\5\26\f\2\u0206\u020f\7\f\2\2\u0207\u020c\5B\"\2"+
		"\u0208\u0209\7\16\2\2\u0209\u020b\5B\"\2\u020a\u0208\3\2\2\2\u020b\u020e"+
		"\3\2\2\2\u020c\u020a\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u0210\3\2\2\2\u020e"+
		"\u020c\3\2\2\2\u020f\u0207\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0211\3\2"+
		"\2\2\u0211\u0212\7\r\2\2\u0212\u0213\b\62\1\2\u0213c\3\2\2\2\u0214\u0215"+
		"\5h\65\2\u0215\u0216\b\63\1\2\u0216\u021e\3\2\2\2\u0217\u0218\5j\66\2"+
		"\u0218\u0219\b\63\1\2\u0219\u021e\3\2\2\2\u021a\u021b\5f\64\2\u021b\u021c"+
		"\b\63\1\2\u021c\u021e\3\2\2\2\u021d\u0214\3\2\2\2\u021d\u0217\3\2\2\2"+
		"\u021d\u021a\3\2\2\2\u021ee\3\2\2\2\u021f\u0220\7,\2\2\u0220\u0225\5B"+
		"\"\2\u0221\u0222\7\16\2\2\u0222\u0224\5B\"\2\u0223\u0221\3\2\2\2\u0224"+
		"\u0227\3\2\2\2\u0225\u0223\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0228\3\2"+
		"\2\2\u0227\u0225\3\2\2\2\u0228\u0229\7-\2\2\u0229\u022a\b\64\1\2\u022a"+
		"g\3\2\2\2\u022b\u022c\7\60\2\2\u022c\u022d\b\65\1\2\u022di\3\2\2\2\u022e"+
		"\u022f\7\61\2\2\u022f\u0230\b\66\1\2\u0230k\3\2\2\2.pr\u008b\u0096\u009a"+
		"\u00a8\u00ac\u00b8\u00bb\u00c4\u00c7\u00ce\u00d7\u00e1\u00ee\u0102\u0107"+
		"\u010b\u010f\u0113\u011b\u012a\u0145\u014c\u0155\u0160\u0169\u0180\u018b"+
		"\u0193\u019e\u01a6\u01af\u01b7\u01c0\u01c8\u01d7\u01df\u01fb\u0203\u020c"+
		"\u020f\u021d\u0225";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
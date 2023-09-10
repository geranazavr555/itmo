// Generated from C:/Programing/sem5/parsers/lab3a/src/main/antlr4\Kek.g4 by ANTLR 4.9.2
package kek.grammar;

    import kek.translation.*;

	import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
	import org.antlr.v4.runtime.tree.*;
import java.util.List;
	import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KekParser extends Parser {
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
	public static final int
		RULE_file = 0, RULE_varTyped = 1, RULE_varDecl = 2, RULE_typeAnnotation = 3, 
		RULE_primitiveType = 4, RULE_importDecl = 5, RULE_moduleName = 6, RULE_funcDecl = 7, 
		RULE_funcName = 8, RULE_funcArgsDecl = 9, RULE_funcModifiers = 10, RULE_funcModifier = 11, 
		RULE_externModifier = 12, RULE_block = 13, RULE_statement = 14, RULE_flowStatement = 15, 
		RULE_returnStatement = 16, RULE_breakStatement = 17, RULE_continueStatement = 18, 
		RULE_ifStatement = 19, RULE_ifPositivePart = 20, RULE_ifNegativePart = 21, 
		RULE_whileStatement = 22, RULE_simpleStatement = 23, RULE_assignStatement = 24, 
		RULE_var = 25, RULE_varUsage = 26, RULE_expression = 27, RULE_operator = 28, 
		RULE_orOperator = 29, RULE_or = 30, RULE_andOperator = 31, RULE_and = 32, 
		RULE_equalsOperator = 33, RULE_equals = 34, RULE_plusOperator = 35, RULE_plus = 36, 
		RULE_mulOperator = 37, RULE_mul = 38, RULE_notOperator = 39, RULE_not = 40, 
		RULE_funcCall = 41, RULE_literal = 42, RULE_intLiteral = 43, RULE_boolLiteral = 44;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "varTyped", "varDecl", "typeAnnotation", "primitiveType", "importDecl", 
			"moduleName", "funcDecl", "funcName", "funcArgsDecl", "funcModifiers", 
			"funcModifier", "externModifier", "block", "statement", "flowStatement", 
			"returnStatement", "breakStatement", "continueStatement", "ifStatement", 
			"ifPositivePart", "ifNegativePart", "whileStatement", "simpleStatement", 
			"assignStatement", "var", "varUsage", "expression", "operator", "orOperator", 
			"or", "andOperator", "and", "equalsOperator", "equals", "plusOperator", 
			"plus", "mulOperator", "mul", "notOperator", "not", "funcCall", "literal", 
			"intLiteral", "boolLiteral"
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

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << IMPORT) | (1L << DEF))) != 0)) {
				{
				setState(94);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(91);
					((FileContext)_localctx).importDecl = importDecl();
					((FileContext)_localctx).importDecls.add(((FileContext)_localctx).importDecl);
					}
					break;
				case LET:
					{
					setState(92);
					((FileContext)_localctx).varDecl = varDecl();
					((FileContext)_localctx).varDecls.add(((FileContext)_localctx).varDecl);
					}
					break;
				case DEF:
					{
					setState(93);
					((FileContext)_localctx).funcDecl = funcDecl();
					((FileContext)_localctx).funcDecls.add(((FileContext)_localctx).funcDecl);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
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
			setState(102);
			var();
			setState(103);
			((VarTypedContext)_localctx).typeAnnotation = typeAnnotation();

			    ((VarTypedContext)_localctx).kekVar =  new KekVar(((VarTypedContext)_localctx).typeAnnotation.kekPrimitiveType, _localctx.getChild(0).getText());

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
			setState(106);
			match(LET);
			setState(107);
			((VarDeclContext)_localctx).varTyped = varTyped();
			setState(108);
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
		public KekPrimitiveType kekPrimitiveType;
		public PrimitiveTypeContext primitiveType;
		public TerminalNode COLON() { return getToken(KekParser.COLON, 0); }
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
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
			setState(111);
			match(COLON);
			setState(112);
			((TypeAnnotationContext)_localctx).primitiveType = primitiveType();
			((TypeAnnotationContext)_localctx).kekPrimitiveType =  ((TypeAnnotationContext)_localctx).primitiveType.kekPrimitiveType;
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
		public KekPrimitiveType kekPrimitiveType;
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
		enterRule(_localctx, 8, RULE_primitiveType);
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				match(INT);
				((PrimitiveTypeContext)_localctx).kekPrimitiveType =  KekPrimitiveType.INT;
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(BOOL);
				((PrimitiveTypeContext)_localctx).kekPrimitiveType =  KekPrimitiveType.BOOL;
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
		enterRule(_localctx, 10, RULE_importDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(IMPORT);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTERN) {
				{
				setState(122);
				match(EXTERN);
				}
			}

			setState(125);
			moduleName();
			setState(126);
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
		enterRule(_localctx, 12, RULE_moduleName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
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
		enterRule(_localctx, 14, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(DEF);
			setState(131);
			((FuncDeclContext)_localctx).funcName = funcName();
			setState(132);
			match(LPAR);
			setState(133);
			((FuncDeclContext)_localctx).funcArgsDecl = funcArgsDecl();
			setState(134);
			match(RPAR);
			setState(135);
			((FuncDeclContext)_localctx).typeAnnotation = typeAnnotation();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFER || _la==EXTERN) {
				{
				setState(136);
				((FuncDeclContext)_localctx).funcModifiers = funcModifiers();
				((FuncDeclContext)_localctx).fm.add(((FuncDeclContext)_localctx).funcModifiers);
				}
			}

			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(139);
				((FuncDeclContext)_localctx).block = block();
				((FuncDeclContext)_localctx).blocks.add(((FuncDeclContext)_localctx).block);
				}
				break;
			case EOLN:
				{
				setState(140);
				match(EOLN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			    KekFunc kekFunc = new KekFunc(
			        ((FuncDeclContext)_localctx).funcName.name,
			        ((FuncDeclContext)_localctx).typeAnnotation.kekPrimitiveType,
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
		enterRule(_localctx, 16, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		enterRule(_localctx, 18, RULE_funcArgsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(148);
				((FuncArgsDeclContext)_localctx).varTyped = varTyped();
				((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(149);
					match(COMMA);
					setState(150);
					((FuncArgsDeclContext)_localctx).varTyped = varTyped();
					((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
					}
					}
					setState(155);
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
		enterRule(_localctx, 20, RULE_funcModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160);
				((FuncModifiersContext)_localctx).funcModifier = funcModifier();
				((FuncModifiersContext)_localctx).mods.add(((FuncModifiersContext)_localctx).funcModifier);
				}
				}
				setState(163); 
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
		enterRule(_localctx, 22, RULE_funcModifier);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFER:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				match(DEFER);
				((FuncModifierContext)_localctx).kekModifier =  KekModifier.DEFER;
				}
				break;
			case EXTERN:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
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
		enterRule(_localctx, 24, RULE_externModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
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
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(LBRACE);
			setState(177);
			match(EOLN);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << LPAR) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				{
				setState(178);
				((BlockContext)_localctx).statement = statement();
				((BlockContext)_localctx).statements.add(((BlockContext)_localctx).statement);
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(RBRACE);
			setState(185);
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
		enterRule(_localctx, 28, RULE_statement);
		try {
			setState(194);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
			case LPAR:
			case NOT:
			case NOT_SIGN:
			case NOT_SIGN_BITWISE:
			case INT_LITERAL:
			case BOOL_LITERAL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				((StatementContext)_localctx).simpleStatement = simpleStatement();
				((StatementContext)_localctx).kekStatement =  ((StatementContext)_localctx).simpleStatement.kekStatement;
				}
				break;
			case BREAK:
			case CONTINUE:
			case IF:
			case WHILE:
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
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
		public ReturnStatementContext returnStatement;
		public BreakStatementContext breakStatement;
		public ContinueStatementContext continueStatement;
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
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
		enterRule(_localctx, 30, RULE_flowStatement);
		try {
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				((FlowStatementContext)_localctx).ifStatement = ifStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).ifStatement.kekIf;
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				((FlowStatementContext)_localctx).whileStatement = whileStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).whileStatement.kekWhile;
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				((FlowStatementContext)_localctx).returnStatement = returnStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).returnStatement.kekReturn;
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				((FlowStatementContext)_localctx).breakStatement = breakStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).breakStatement.kekStatement;
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(208);
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
		enterRule(_localctx, 32, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(RETURN);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(214);
				((ReturnStatementContext)_localctx).expression = expression();
				}
			}

			setState(217);
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
		enterRule(_localctx, 34, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(BREAK);
			setState(221);
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
		enterRule(_localctx, 36, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(CONTINUE);
			setState(225);
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
		enterRule(_localctx, 38, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			((IfStatementContext)_localctx).ifPositivePart = ifPositivePart();
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(229);
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
		enterRule(_localctx, 40, RULE_ifPositivePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(IF);
			setState(235);
			((IfPositivePartContext)_localctx).expression = expression();
			setState(236);
			((IfPositivePartContext)_localctx).block = block();

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
		enterRule(_localctx, 42, RULE_ifNegativePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(ELSE);
			setState(240);
			((IfNegativePartContext)_localctx).block = block();
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
		enterRule(_localctx, 44, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(WHILE);
			setState(244);
			((WhileStatementContext)_localctx).expression = expression();
			setState(245);
			((WhileStatementContext)_localctx).block = block();
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
		enterRule(_localctx, 46, RULE_simpleStatement);
		try {
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				((SimpleStatementContext)_localctx).assignStatement = assignStatement();
				((SimpleStatementContext)_localctx).kekStatement =  ((SimpleStatementContext)_localctx).assignStatement.kekAssign;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(252);
				((SimpleStatementContext)_localctx).expression = expression();
				setState(253);
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
		enterRule(_localctx, 48, RULE_assignStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			((AssignStatementContext)_localctx).varUsage = varUsage();
			((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(260);
				match(COMMA);
				setState(261);
				((AssignStatementContext)_localctx).varUsage = varUsage();
				((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(267);
			match(ASSIGN);
			setState(268);
			((AssignStatementContext)_localctx).expression = expression();
			((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(269);
				match(COMMA);
				setState(270);
				((AssignStatementContext)_localctx).expression = expression();
				((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276);
			match(EOLN);

			    if (((AssignStatementContext)_localctx).varUsages.size() != ((AssignStatementContext)_localctx).exprs.size()) {
			        throw new RuntimeException("Expected the same count of vars and expressions");
			    }

			    ((AssignStatementContext)_localctx).kekAssign =  new KekAssign(
			        ((AssignStatementContext)_localctx).varUsages.stream().map(x -> x.kekVarUsage).collect(Collectors.toList()),
			        ((AssignStatementContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList())
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
		enterRule(_localctx, 50, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
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
		enterRule(_localctx, 52, RULE_varUsage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
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
		enterRule(_localctx, 54, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
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
		enterRule(_localctx, 56, RULE_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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
		enterRule(_localctx, 58, RULE_orOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			((OrOperatorContext)_localctx).andOperator = andOperator();
			((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292);
					((OrOperatorContext)_localctx).or = or();
					((OrOperatorContext)_localctx).rules.add(((OrOperatorContext)_localctx).or);
					setState(293);
					((OrOperatorContext)_localctx).andOperator = andOperator();
					((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		enterRule(_localctx, 60, RULE_or);
		try {
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				match(OR);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				match(OR_SIGN);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(306);
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
		enterRule(_localctx, 62, RULE_andOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
			((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
			setState(316);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					((AndOperatorContext)_localctx).and = and();
					((AndOperatorContext)_localctx).rules.add(((AndOperatorContext)_localctx).and);
					setState(312);
					((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
					((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
					}
					} 
				}
				setState(318);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 64, RULE_and);
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				match(AND);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(AND_SIGN);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
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
		enterRule(_localctx, 66, RULE_equalsOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
			((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(330);
					((EqualsOperatorContext)_localctx).equals = equals();
					((EqualsOperatorContext)_localctx).rules.add(((EqualsOperatorContext)_localctx).equals);
					setState(331);
					((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
					((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 68, RULE_equals);
		try {
			setState(344);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				match(EQUALS);
				((EqualsContext)_localctx).kekOperatorRule =  KekOperatorRule.EQUALS;
				}
				break;
			case NOT_EQUALS:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
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
		enterRule(_localctx, 70, RULE_plusOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			((PlusOperatorContext)_localctx).mulOperator = mulOperator();
			((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
			setState(352);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(347);
					((PlusOperatorContext)_localctx).plus = plus();
					((PlusOperatorContext)_localctx).rules.add(((PlusOperatorContext)_localctx).plus);
					setState(348);
					((PlusOperatorContext)_localctx).mulOperator = mulOperator();
					((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
					}
					} 
				}
				setState(354);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
		enterRule(_localctx, 72, RULE_plus);
		try {
			setState(361);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				match(PLUS_SIGN);
				((PlusContext)_localctx).kekOperatorRule =  KekOperatorRule.PLUS;
				}
				break;
			case MINUS_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
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
		public NotOperatorContext notOperator;
		public List<NotOperatorContext> lst = new ArrayList<NotOperatorContext>();
		public MulContext mul;
		public List<MulContext> rules = new ArrayList<MulContext>();
		public List<NotOperatorContext> notOperator() {
			return getRuleContexts(NotOperatorContext.class);
		}
		public NotOperatorContext notOperator(int i) {
			return getRuleContext(NotOperatorContext.class,i);
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
		enterRule(_localctx, 74, RULE_mulOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			((MulOperatorContext)_localctx).notOperator = notOperator();
			((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).notOperator);
			setState(369);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(364);
					((MulOperatorContext)_localctx).mul = mul();
					((MulOperatorContext)_localctx).rules.add(((MulOperatorContext)_localctx).mul);
					setState(365);
					((MulOperatorContext)_localctx).notOperator = notOperator();
					((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).notOperator);
					}
					} 
				}
				setState(371);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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
		enterRule(_localctx, 76, RULE_mul);
		try {
			setState(384);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MUL_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				match(MUL_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MUL;
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				match(DIV);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case DIV_SIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				match(DIV_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case MOD:
				enterOuterAlt(_localctx, 4);
				{
				setState(380);
				match(MOD);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MOD;
				}
				break;
			case MOD_SIGN:
				enterOuterAlt(_localctx, 5);
				{
				setState(382);
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
		enterRule(_localctx, 78, RULE_notOperator);
		try {
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(386);
				match(LPAR);
				setState(387);
				((NotOperatorContext)_localctx).expression = expression();
				setState(388);
				match(RPAR);
				}
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).expression.kekExpr;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(392);
				((NotOperatorContext)_localctx).not = not();
				setState(393);
				((NotOperatorContext)_localctx).expression = expression();
				}
				((NotOperatorContext)_localctx).kekExpr =  new KekOperator(((NotOperatorContext)_localctx).not.kekOperatorRule, ((NotOperatorContext)_localctx).expression.kekExpr);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(397);
				((NotOperatorContext)_localctx).funcCall = funcCall();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).funcCall.kekFuncCall;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(400);
				((NotOperatorContext)_localctx).varUsage = varUsage();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).varUsage.kekVarUsage;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(403);
				((NotOperatorContext)_localctx).literal = literal();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).literal.kekLiteral;
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
		enterRule(_localctx, 80, RULE_not);
		try {
			setState(414);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
				match(NOT);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(410);
				match(NOT_SIGN);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(412);
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
		enterRule(_localctx, 82, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			((FuncCallContext)_localctx).funcName = funcName();
			setState(417);
			match(LPAR);
			setState(426);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(418);
				((FuncCallContext)_localctx).expression = expression();
				((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(419);
					match(COMMA);
					setState(420);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(425);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(428);
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
		public KekLiteral kekLiteral;
		public IntLiteralContext intLiteral;
		public BoolLiteralContext boolLiteral;
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
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
		enterRule(_localctx, 84, RULE_literal);
		try {
			setState(437);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(431);
				((LiteralContext)_localctx).intLiteral = intLiteral();
				((LiteralContext)_localctx).kekLiteral =  ((LiteralContext)_localctx).intLiteral.kekLiteral;
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(434);
				((LiteralContext)_localctx).boolLiteral = boolLiteral();
				((LiteralContext)_localctx).kekLiteral =  ((LiteralContext)_localctx).boolLiteral.kekLiteral;
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
		enterRule(_localctx, 86, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
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
		enterRule(_localctx, 88, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u01c0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\7\2a\n\2\f\2\16\2d\13\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6"+
		"z\n\6\3\7\3\7\5\7~\n\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u008c\n\t\3\t\3\t\5\t\u0090\n\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\7\13\u009a\n\13\f\13\16\13\u009d\13\13\5\13\u009f\n\13\3\13\3\13\3\f"+
		"\6\f\u00a4\n\f\r\f\16\f\u00a5\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u00af\n"+
		"\r\3\16\3\16\3\17\3\17\3\17\7\17\u00b6\n\17\f\17\16\17\u00b9\13\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00c5\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00d6\n\21\3\22\3\22\5\22\u00da\n\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\25\3\25\5\25\u00e9\n\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0104\n\31\3\32\3\32\3\32\7\32"+
		"\u0109\n\32\f\32\16\32\u010c\13\32\3\32\3\32\3\32\3\32\7\32\u0112\n\32"+
		"\f\32\16\32\u0115\13\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\7\37\u012a\n\37\f\37"+
		"\16\37\u012d\13\37\3\37\3\37\3 \3 \3 \3 \3 \3 \5 \u0137\n \3!\3!\3!\3"+
		"!\7!\u013d\n!\f!\16!\u0140\13!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u014a"+
		"\n\"\3#\3#\3#\3#\7#\u0150\n#\f#\16#\u0153\13#\3#\3#\3$\3$\3$\3$\5$\u015b"+
		"\n$\3%\3%\3%\3%\7%\u0161\n%\f%\16%\u0164\13%\3%\3%\3&\3&\3&\3&\5&\u016c"+
		"\n&\3\'\3\'\3\'\3\'\7\'\u0172\n\'\f\'\16\'\u0175\13\'\3\'\3\'\3(\3(\3"+
		"(\3(\3(\3(\3(\3(\3(\3(\5(\u0183\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0199\n)\3*\3*\3*\3*\3*\3*\5*\u01a1\n*\3"+
		"+\3+\3+\3+\3+\7+\u01a8\n+\f+\16+\u01ab\13+\5+\u01ad\n+\3+\3+\3+\3,\3,"+
		"\3,\3,\3,\3,\5,\u01b8\n,\3-\3-\3-\3.\3.\3.\3.\2\2/\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\2\2\2\u01c1"+
		"\2\\\3\2\2\2\4h\3\2\2\2\6l\3\2\2\2\bq\3\2\2\2\ny\3\2\2\2\f{\3\2\2\2\16"+
		"\u0082\3\2\2\2\20\u0084\3\2\2\2\22\u0093\3\2\2\2\24\u009e\3\2\2\2\26\u00a3"+
		"\3\2\2\2\30\u00ae\3\2\2\2\32\u00b0\3\2\2\2\34\u00b2\3\2\2\2\36\u00c4\3"+
		"\2\2\2 \u00d5\3\2\2\2\"\u00d7\3\2\2\2$\u00de\3\2\2\2&\u00e2\3\2\2\2(\u00e6"+
		"\3\2\2\2*\u00ec\3\2\2\2,\u00f1\3\2\2\2.\u00f5\3\2\2\2\60\u0103\3\2\2\2"+
		"\62\u0105\3\2\2\2\64\u0119\3\2\2\2\66\u011c\3\2\2\28\u011f\3\2\2\2:\u0122"+
		"\3\2\2\2<\u0125\3\2\2\2>\u0136\3\2\2\2@\u0138\3\2\2\2B\u0149\3\2\2\2D"+
		"\u014b\3\2\2\2F\u015a\3\2\2\2H\u015c\3\2\2\2J\u016b\3\2\2\2L\u016d\3\2"+
		"\2\2N\u0182\3\2\2\2P\u0198\3\2\2\2R\u01a0\3\2\2\2T\u01a2\3\2\2\2V\u01b7"+
		"\3\2\2\2X\u01b9\3\2\2\2Z\u01bc\3\2\2\2\\b\b\2\1\2]a\5\f\7\2^a\5\6\4\2"+
		"_a\5\20\t\2`]\3\2\2\2`^\3\2\2\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2"+
		"\2ce\3\2\2\2db\3\2\2\2ef\7\2\2\3fg\b\2\1\2g\3\3\2\2\2hi\5\64\33\2ij\5"+
		"\b\5\2jk\b\3\1\2k\5\3\2\2\2lm\7\3\2\2mn\5\4\3\2no\7.\2\2op\b\4\1\2p\7"+
		"\3\2\2\2qr\7\4\2\2rs\5\n\6\2st\b\5\1\2t\t\3\2\2\2uv\7\5\2\2vz\b\6\1\2"+
		"wx\7\6\2\2xz\b\6\1\2yu\3\2\2\2yw\3\2\2\2z\13\3\2\2\2{}\7\t\2\2|~\7\30"+
		"\2\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\5\16\b\2\u0080\u0081\7"+
		".\2\2\u0081\r\3\2\2\2\u0082\u0083\7-\2\2\u0083\17\3\2\2\2\u0084\u0085"+
		"\7\n\2\2\u0085\u0086\5\22\n\2\u0086\u0087\7\13\2\2\u0087\u0088\5\24\13"+
		"\2\u0088\u0089\7\f\2\2\u0089\u008b\5\b\5\2\u008a\u008c\5\26\f\2\u008b"+
		"\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u0090\5\34"+
		"\17\2\u008e\u0090\7.\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0092\b\t\1\2\u0092\21\3\2\2\2\u0093\u0094\7-\2\2"+
		"\u0094\u0095\b\n\1\2\u0095\23\3\2\2\2\u0096\u009b\5\4\3\2\u0097\u0098"+
		"\7\r\2\2\u0098\u009a\5\4\3\2\u0099\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u0096\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a1\b\13\1\2\u00a1\25\3\2\2\2\u00a2\u00a4\5\30\r\2\u00a3\u00a2\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a8\b\f\1\2\u00a8\27\3\2\2\2\u00a9\u00aa\7\16\2"+
		"\2\u00aa\u00af\b\r\1\2\u00ab\u00ac\5\32\16\2\u00ac\u00ad\b\r\1\2\u00ad"+
		"\u00af\3\2\2\2\u00ae\u00a9\3\2\2\2\u00ae\u00ab\3\2\2\2\u00af\31\3\2\2"+
		"\2\u00b0\u00b1\7\30\2\2\u00b1\33\3\2\2\2\u00b2\u00b3\7\17\2\2\u00b3\u00b7"+
		"\7.\2\2\u00b4\u00b6\5\36\20\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2"+
		"\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7"+
		"\3\2\2\2\u00ba\u00bb\7\20\2\2\u00bb\u00bc\7.\2\2\u00bc\u00bd\b\17\1\2"+
		"\u00bd\35\3\2\2\2\u00be\u00bf\5\60\31\2\u00bf\u00c0\b\20\1\2\u00c0\u00c5"+
		"\3\2\2\2\u00c1\u00c2\5 \21\2\u00c2\u00c3\b\20\1\2\u00c3\u00c5\3\2\2\2"+
		"\u00c4\u00be\3\2\2\2\u00c4\u00c1\3\2\2\2\u00c5\37\3\2\2\2\u00c6\u00c7"+
		"\5(\25\2\u00c7\u00c8\b\21\1\2\u00c8\u00d6\3\2\2\2\u00c9\u00ca\5.\30\2"+
		"\u00ca\u00cb\b\21\1\2\u00cb\u00d6\3\2\2\2\u00cc\u00cd\5\"\22\2\u00cd\u00ce"+
		"\b\21\1\2\u00ce\u00d6\3\2\2\2\u00cf\u00d0\5$\23\2\u00d0\u00d1\b\21\1\2"+
		"\u00d1\u00d6\3\2\2\2\u00d2\u00d3\5&\24\2\u00d3\u00d4\b\21\1\2\u00d4\u00d6"+
		"\3\2\2\2\u00d5\u00c6\3\2\2\2\u00d5\u00c9\3\2\2\2\u00d5\u00cc\3\2\2\2\u00d5"+
		"\u00cf\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d6!\3\2\2\2\u00d7\u00d9\7\27\2\2"+
		"\u00d8\u00da\58\35\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db"+
		"\3\2\2\2\u00db\u00dc\7.\2\2\u00dc\u00dd\b\22\1\2\u00dd#\3\2\2\2\u00de"+
		"\u00df\7\21\2\2\u00df\u00e0\7.\2\2\u00e0\u00e1\b\23\1\2\u00e1%\3\2\2\2"+
		"\u00e2\u00e3\7\22\2\2\u00e3\u00e4\7.\2\2\u00e4\u00e5\b\24\1\2\u00e5\'"+
		"\3\2\2\2\u00e6\u00e8\5*\26\2\u00e7\u00e9\5,\27\2\u00e8\u00e7\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\b\25\1\2\u00eb)\3\2\2\2"+
		"\u00ec\u00ed\7\23\2\2\u00ed\u00ee\58\35\2\u00ee\u00ef\5\34\17\2\u00ef"+
		"\u00f0\b\26\1\2\u00f0+\3\2\2\2\u00f1\u00f2\7\24\2\2\u00f2\u00f3\5\34\17"+
		"\2\u00f3\u00f4\b\27\1\2\u00f4-\3\2\2\2\u00f5\u00f6\7\25\2\2\u00f6\u00f7"+
		"\58\35\2\u00f7\u00f8\5\34\17\2\u00f8\u00f9\b\30\1\2\u00f9/\3\2\2\2\u00fa"+
		"\u0104\5\6\4\2\u00fb\u00fc\5\62\32\2\u00fc\u00fd\b\31\1\2\u00fd\u0104"+
		"\3\2\2\2\u00fe\u00ff\58\35\2\u00ff\u0100\7.\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0102\b\31\1\2\u0102\u0104\3\2\2\2\u0103\u00fa\3\2\2\2\u0103\u00fb\3"+
		"\2\2\2\u0103\u00fe\3\2\2\2\u0104\61\3\2\2\2\u0105\u010a\5\66\34\2\u0106"+
		"\u0107\7\r\2\2\u0107\u0109\5\66\34\2\u0108\u0106\3\2\2\2\u0109\u010c\3"+
		"\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010d\u010e\7\26\2\2\u010e\u0113\58\35\2\u010f\u0110\7"+
		"\r\2\2\u0110\u0112\58\35\2\u0111\u010f\3\2\2\2\u0112\u0115\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2"+
		"\2\2\u0116\u0117\7.\2\2\u0117\u0118\b\32\1\2\u0118\63\3\2\2\2\u0119\u011a"+
		"\7-\2\2\u011a\u011b\b\33\1\2\u011b\65\3\2\2\2\u011c\u011d\5\64\33\2\u011d"+
		"\u011e\b\34\1\2\u011e\67\3\2\2\2\u011f\u0120\5:\36\2\u0120\u0121\b\35"+
		"\1\2\u01219\3\2\2\2\u0122\u0123\5<\37\2\u0123\u0124\b\36\1\2\u0124;\3"+
		"\2\2\2\u0125\u012b\5@!\2\u0126\u0127\5> \2\u0127\u0128\5@!\2\u0128\u012a"+
		"\3\2\2\2\u0129\u0126\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u012e\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u012f\b\37"+
		"\1\2\u012f=\3\2\2\2\u0130\u0131\7\31\2\2\u0131\u0137\b \1\2\u0132\u0133"+
		"\7\33\2\2\u0133\u0137\b \1\2\u0134\u0135\7\32\2\2\u0135\u0137\b \1\2\u0136"+
		"\u0130\3\2\2\2\u0136\u0132\3\2\2\2\u0136\u0134\3\2\2\2\u0137?\3\2\2\2"+
		"\u0138\u013e\5D#\2\u0139\u013a\5B\"\2\u013a\u013b\5D#\2\u013b\u013d\3"+
		"\2\2\2\u013c\u0139\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e"+
		"\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0142\b!"+
		"\1\2\u0142A\3\2\2\2\u0143\u0144\7\34\2\2\u0144\u014a\b\"\1\2\u0145\u0146"+
		"\7\36\2\2\u0146\u014a\b\"\1\2\u0147\u0148\7\35\2\2\u0148\u014a\b\"\1\2"+
		"\u0149\u0143\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0147\3\2\2\2\u014aC\3"+
		"\2\2\2\u014b\u0151\5H%\2\u014c\u014d\5F$\2\u014d\u014e\5H%\2\u014e\u0150"+
		"\3\2\2\2\u014f\u014c\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\b#"+
		"\1\2\u0155E\3\2\2\2\u0156\u0157\7)\2\2\u0157\u015b\b$\1\2\u0158\u0159"+
		"\7*\2\2\u0159\u015b\b$\1\2\u015a\u0156\3\2\2\2\u015a\u0158\3\2\2\2\u015b"+
		"G\3\2\2\2\u015c\u0162\5L\'\2\u015d\u015e\5J&\2\u015e\u015f\5L\'\2\u015f"+
		"\u0161\3\2\2\2\u0160\u015d\3\2\2\2\u0161\u0164\3\2\2\2\u0162\u0160\3\2"+
		"\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164\u0162\3\2\2\2\u0165"+
		"\u0166\b%\1\2\u0166I\3\2\2\2\u0167\u0168\7\"\2\2\u0168\u016c\b&\1\2\u0169"+
		"\u016a\7#\2\2\u016a\u016c\b&\1\2\u016b\u0167\3\2\2\2\u016b\u0169\3\2\2"+
		"\2\u016cK\3\2\2\2\u016d\u0173\5P)\2\u016e\u016f\5N(\2\u016f\u0170\5P)"+
		"\2\u0170\u0172\3\2\2\2\u0171\u016e\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171"+
		"\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0173\3\2\2\2\u0176"+
		"\u0177\b\'\1\2\u0177M\3\2\2\2\u0178\u0179\7$\2\2\u0179\u0183\b(\1\2\u017a"+
		"\u017b\7%\2\2\u017b\u0183\b(\1\2\u017c\u017d\7&\2\2\u017d\u0183\b(\1\2"+
		"\u017e\u017f\7\'\2\2\u017f\u0183\b(\1\2\u0180\u0181\7(\2\2\u0181\u0183"+
		"\b(\1\2\u0182\u0178\3\2\2\2\u0182\u017a\3\2\2\2\u0182\u017c\3\2\2\2\u0182"+
		"\u017e\3\2\2\2\u0182\u0180\3\2\2\2\u0183O\3\2\2\2\u0184\u0185\7\13\2\2"+
		"\u0185\u0186\58\35\2\u0186\u0187\7\f\2\2\u0187\u0188\3\2\2\2\u0188\u0189"+
		"\b)\1\2\u0189\u0199\3\2\2\2\u018a\u018b\5R*\2\u018b\u018c\58\35\2\u018c"+
		"\u018d\3\2\2\2\u018d\u018e\b)\1\2\u018e\u0199\3\2\2\2\u018f\u0190\5T+"+
		"\2\u0190\u0191\b)\1\2\u0191\u0199\3\2\2\2\u0192\u0193\5\66\34\2\u0193"+
		"\u0194\b)\1\2\u0194\u0199\3\2\2\2\u0195\u0196\5V,\2\u0196\u0197\b)\1\2"+
		"\u0197\u0199\3\2\2\2\u0198\u0184\3\2\2\2\u0198\u018a\3\2\2\2\u0198\u018f"+
		"\3\2\2\2\u0198\u0192\3\2\2\2\u0198\u0195\3\2\2\2\u0199Q\3\2\2\2\u019a"+
		"\u019b\7\37\2\2\u019b\u01a1\b*\1\2\u019c\u019d\7 \2\2\u019d\u01a1\b*\1"+
		"\2\u019e\u019f\7!\2\2\u019f\u01a1\b*\1\2\u01a0\u019a\3\2\2\2\u01a0\u019c"+
		"\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1S\3\2\2\2\u01a2\u01a3\5\22\n\2\u01a3"+
		"\u01ac\7\13\2\2\u01a4\u01a9\58\35\2\u01a5\u01a6\7\r\2\2\u01a6\u01a8\5"+
		"8\35\2\u01a7\u01a5\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9"+
		"\u01aa\3\2\2\2\u01aa\u01ad\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ac\u01a4\3\2"+
		"\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\7\f\2\2\u01af"+
		"\u01b0\b+\1\2\u01b0U\3\2\2\2\u01b1\u01b2\5X-\2\u01b2\u01b3\b,\1\2\u01b3"+
		"\u01b8\3\2\2\2\u01b4\u01b5\5Z.\2\u01b5\u01b6\b,\1\2\u01b6\u01b8\3\2\2"+
		"\2\u01b7\u01b1\3\2\2\2\u01b7\u01b4\3\2\2\2\u01b8W\3\2\2\2\u01b9\u01ba"+
		"\7+\2\2\u01ba\u01bb\b-\1\2\u01bbY\3\2\2\2\u01bc\u01bd\7,\2\2\u01bd\u01be"+
		"\b.\1\2\u01be[\3\2\2\2#`by}\u008b\u008f\u009b\u009e\u00a5\u00ae\u00b7"+
		"\u00c4\u00d5\u00d9\u00e8\u0103\u010a\u0113\u012b\u0136\u013e\u0149\u0151"+
		"\u015a\u0162\u016b\u0173\u0182\u0198\u01a0\u01a9\u01ac\u01b7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
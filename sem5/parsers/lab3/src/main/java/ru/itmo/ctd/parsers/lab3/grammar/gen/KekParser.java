// Generated from C:/Programing/sem5/parsers/lab3/src/main/java/ru/itmo/ctd/parsers/lab3/grammar\Kek.g4 by ANTLR 4.9.2
package ru.itmo.ctd.parsers.lab3.grammar.gen;

    import ru.itmo.ctd.parsers.lab3.translation.*;
    import java.util.stream.Collectors;

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
		INT_TYPE=1, BOOL_TYPE=2, COLON=3, RETURN=4, CONTINUE=5, BREAK=6, ELSE=7, 
		IF=8, WHILE=9, MOD=10, DIV=11, MUL=12, MINUS=13, PLUS=14, NOT=15, AND=16, 
		OR=17, LET=18, DEF=19, LPAR=20, RPAR=21, LBRACE=22, RBRACE=23, COMMA=24, 
		ASSIGN=25, BOOL=26, INT=27, ID=28, BLOCK_COMMENT=29, LINE_COMMENT_1=30, 
		LINE_COMMENT_2=31, EOLN=32, WS=33;
	public static final int
		RULE_file = 0, RULE_var_declaration = 1, RULE_func_declaration = 2, RULE_func_args = 3, 
		RULE_func_arg = 4, RULE_type_annotation = 5, RULE_type = 6, RULE_primitive_type = 7, 
		RULE_block = 8, RULE_statement = 9, RULE_flow_statement = 10, RULE_return_statement = 11, 
		RULE_break_statement = 12, RULE_continue_statement = 13, RULE_if_statement = 14, 
		RULE_if_true_part = 15, RULE_if_false_part = 16, RULE_while_statement = 17, 
		RULE_simple_statement = 18, RULE_assign_statement = 19, RULE_expression = 20, 
		RULE_var_usage = 21, RULE_or_expr = 22, RULE_or_expr_cont = 23, RULE_and_expr = 24, 
		RULE_and_expr_cont = 25, RULE_not_expr = 26, RULE_not_expr_cont = 27, 
		RULE_additive_expr = 28, RULE_additive_expr_cont = 29, RULE_multiplicative_expr = 30, 
		RULE_multiplicative_expr_cont = 31, RULE_terminal_expr = 32, RULE_func_call = 33, 
		RULE_literal = 34, RULE_int_literal = 35, RULE_bool_literal = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "var_declaration", "func_declaration", "func_args", "func_arg", 
			"type_annotation", "type", "primitive_type", "block", "statement", "flow_statement", 
			"return_statement", "break_statement", "continue_statement", "if_statement", 
			"if_true_part", "if_false_part", "while_statement", "simple_statement", 
			"assign_statement", "expression", "var_usage", "or_expr", "or_expr_cont", 
			"and_expr", "and_expr_cont", "not_expr", "not_expr_cont", "additive_expr", 
			"additive_expr_cont", "multiplicative_expr", "multiplicative_expr_cont", 
			"terminal_expr", "func_call", "literal", "int_literal", "bool_literal"
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
		public KekContextManager contextManager;
		public KekFile kekFile;
		public Var_declarationContext var_declaration;
		public List<Var_declarationContext> vars = new ArrayList<Var_declarationContext>();
		public Func_declarationContext func_declaration;
		public List<Func_declarationContext> funcs = new ArrayList<Func_declarationContext>();
		public List<Var_declarationContext> var_declaration() {
			return getRuleContexts(Var_declarationContext.class);
		}
		public Var_declarationContext var_declaration(int i) {
			return getRuleContext(Var_declarationContext.class,i);
		}
		public List<Func_declarationContext> func_declaration() {
			return getRuleContexts(Func_declarationContext.class);
		}
		public Func_declarationContext func_declaration(int i) {
			return getRuleContext(Func_declarationContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FileContext(ParserRuleContext parent, int invokingState, KekContextManager contextManager) {
			super(parent, invokingState);
			this.contextManager = contextManager;
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

	public final FileContext file(KekContextManager contextManager) throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState(), contextManager);
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    this.contextManager = contextManager;

			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(77);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LET:
					{
					setState(75);
					((FileContext)_localctx).var_declaration = var_declaration();
					((FileContext)_localctx).vars.add(((FileContext)_localctx).var_declaration);
					}
					break;
				case DEF:
					{
					setState(76);
					((FileContext)_localctx).func_declaration = func_declaration();
					((FileContext)_localctx).funcs.add(((FileContext)_localctx).func_declaration);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LET || _la==DEF );

			    //KekGlobalContext globalContext = contextManager.getGlobalContext();
			    //((FileContext)_localctx).funcs.stream().map(x -> x.kekFunction).forEach(globalContext::addFunction);
			    //((FileContext)_localctx).vars.stream().map(x -> x.kekVar).forEach(globalContext::addVar);
			    ((FileContext)_localctx).kekFile =  new KekFile(contextManager.getGlobalContext());

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

	public static class Var_declarationContext extends ParserRuleContext {
		public KekVar kekVar;
		public Type_annotationContext type_annotation;
		public TerminalNode LET() { return getToken(KekParser.LET, 0); }
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public Type_annotationContext type_annotation() {
			return getRuleContext(Type_annotationContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVar_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVar_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVar_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_var_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(LET);
			setState(84);
			match(ID);
			setState(85);
			((Var_declarationContext)_localctx).type_annotation = type_annotation();
			setState(86);
			match(EOLN);

			    KekContext context = contextManager.getContext();
			    ((Var_declarationContext)_localctx).kekVar =  new KekVar(_localctx.getChild(1).getText(), ((Var_declarationContext)_localctx).type_annotation.kekType);
			    context.addVar(_localctx.kekVar);

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

	public static class Func_declarationContext extends ParserRuleContext {
		public KekFunction kekFunction;
		public Func_argsContext func_args;
		public Type_annotationContext type_annotation;
		public BlockContext block;
		public TerminalNode DEF() { return getToken(KekParser.DEF, 0); }
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public Type_annotationContext type_annotation() {
			return getRuleContext(Type_annotationContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Func_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFunc_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFunc_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFunc_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_declarationContext func_declaration() throws RecognitionException {
		Func_declarationContext _localctx = new Func_declarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_func_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(DEF);
			setState(90);
			match(ID);
			setState(91);
			match(LPAR);
			setState(92);
			((Func_declarationContext)_localctx).func_args = func_args();
			setState(93);
			match(RPAR);
			setState(94);
			((Func_declarationContext)_localctx).type_annotation = type_annotation();
			setState(95);
			((Func_declarationContext)_localctx).block = block(((Func_declarationContext)_localctx).func_args.args);

			    ((Func_declarationContext)_localctx).kekFunction =  new KekFunction(
			        _localctx.getChild(1).getText(),
			        ((Func_declarationContext)_localctx).type_annotation.kekType,
			        ((Func_declarationContext)_localctx).func_args.args,
			        ((Func_declarationContext)_localctx).block.content
			    );
			    contextManager.getGlobalContext().addFunction(_localctx.kekFunction);

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

	public static class Func_argsContext extends ParserRuleContext {
		public List<KekVar> args;
		public Func_argContext func_arg;
		public List<Func_argContext> argContexts = new ArrayList<Func_argContext>();
		public List<Func_argContext> func_arg() {
			return getRuleContexts(Func_argContext.class);
		}
		public Func_argContext func_arg(int i) {
			return getRuleContext(Func_argContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public Func_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFunc_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFunc_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFunc_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_argsContext func_args() throws RecognitionException {
		Func_argsContext _localctx = new Func_argsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(98);
				((Func_argsContext)_localctx).func_arg = func_arg();
				((Func_argsContext)_localctx).argContexts.add(((Func_argsContext)_localctx).func_arg);
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(99);
					match(COMMA);
					setState(100);
					((Func_argsContext)_localctx).func_arg = func_arg();
					((Func_argsContext)_localctx).argContexts.add(((Func_argsContext)_localctx).func_arg);
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}


			    ((Func_argsContext)_localctx).args =  ((Func_argsContext)_localctx).argContexts.stream().map(x -> x.kekVar).collect(Collectors.toList());

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

	public static class Func_argContext extends ParserRuleContext {
		public KekVar kekVar;
		public Type_annotationContext type_annotation;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public Type_annotationContext type_annotation() {
			return getRuleContext(Type_annotationContext.class,0);
		}
		public Func_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFunc_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFunc_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFunc_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_argContext func_arg() throws RecognitionException {
		Func_argContext _localctx = new Func_argContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(ID);
			setState(111);
			((Func_argContext)_localctx).type_annotation = type_annotation();

			    ((Func_argContext)_localctx).kekVar =  new KekVar(_localctx.getChild(0).getText(), ((Func_argContext)_localctx).type_annotation.kekType);

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

	public static class Type_annotationContext extends ParserRuleContext {
		public KekType kekType;
		public TypeContext type;
		public TerminalNode COLON() { return getToken(KekParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Type_annotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterType_annotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitType_annotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitType_annotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_annotationContext type_annotation() throws RecognitionException {
		Type_annotationContext _localctx = new Type_annotationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(COLON);
			setState(115);
			((Type_annotationContext)_localctx).type = type();
			((Type_annotationContext)_localctx).kekType =  ((Type_annotationContext)_localctx).type.kekType;
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
		public Primitive_typeContext primitive_type;
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
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
		enterRule(_localctx, 12, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((TypeContext)_localctx).primitive_type = primitive_type();
			((TypeContext)_localctx).kekType =  ((TypeContext)_localctx).primitive_type.kekType;
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

	public static class Primitive_typeContext extends ParserRuleContext {
		public KekType kekType;
		public TerminalNode INT_TYPE() { return getToken(KekParser.INT_TYPE, 0); }
		public TerminalNode BOOL_TYPE() { return getToken(KekParser.BOOL_TYPE, 0); }
		public Primitive_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPrimitive_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPrimitive_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPrimitive_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primitive_typeContext primitive_type() throws RecognitionException {
		Primitive_typeContext _localctx = new Primitive_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primitive_type);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(INT_TYPE);
				}
				break;
			case BOOL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(BOOL_TYPE);

				    ((Primitive_typeContext)_localctx).kekType =  KekType.valueOf(_localctx.getChild(0).getText().toUpperCase());

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

	public static class BlockContext extends ParserRuleContext {
		public List<KekVar> additionalVars;
		public KekBlock content;
		public StatementContext statement;
		public List<StatementContext> stmts = new ArrayList<StatementContext>();
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
		enterRule(_localctx, 16, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(LBRACE);
			setState(127);
			match(EOLN);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETURN) | (1L << CONTINUE) | (1L << BREAK) | (1L << IF) | (1L << WHILE) | (1L << LET) | (1L << LPAR) | (1L << BOOL) | (1L << INT) | (1L << ID))) != 0)) {
				{
				{
				setState(128);
				((BlockContext)_localctx).statement = statement();
				((BlockContext)_localctx).stmts.add(((BlockContext)_localctx).statement);
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
			match(RBRACE);
			setState(135);
			match(EOLN);

			    if (_localctx.additionalVars != null) {
			        KekContext context = contextManager.getContext();
			        _localctx.additionalVars.forEach(context::addVar);
			    }
			    ((BlockContext)_localctx).content =  new KekBlock(((BlockContext)_localctx).stmts.stream().map(x -> x.content).collect(Collectors.toList()));

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
		public TranslationUnit content;
		public Simple_statementContext simple_statement;
		public Flow_statementContext flow_statement;
		public Simple_statementContext simple_statement() {
			return getRuleContext(Simple_statementContext.class,0);
		}
		public Flow_statementContext flow_statement() {
			return getRuleContext(Flow_statementContext.class,0);
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
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
			case LPAR:
			case BOOL:
			case INT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				((StatementContext)_localctx).simple_statement = simple_statement();
				((StatementContext)_localctx).content =  ((StatementContext)_localctx).simple_statement.content;
				}
				break;
			case RETURN:
			case CONTINUE:
			case BREAK:
			case IF:
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				((StatementContext)_localctx).flow_statement = flow_statement();
				((StatementContext)_localctx).content =  ((StatementContext)_localctx).flow_statement.content;
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

	public static class Flow_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public If_statementContext if_statement;
		public While_statementContext while_statement;
		public Return_statementContext return_statement;
		public Break_statementContext break_statement;
		public Continue_statementContext continue_statement;
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public Break_statementContext break_statement() {
			return getRuleContext(Break_statementContext.class,0);
		}
		public Continue_statementContext continue_statement() {
			return getRuleContext(Continue_statementContext.class,0);
		}
		public Flow_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFlow_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFlow_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFlow_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Flow_statementContext flow_statement() throws RecognitionException {
		Flow_statementContext _localctx = new Flow_statementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flow_statement);
		try {
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				((Flow_statementContext)_localctx).if_statement = if_statement();
				((Flow_statementContext)_localctx).content =  ((Flow_statementContext)_localctx).if_statement.content;
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				((Flow_statementContext)_localctx).while_statement = while_statement();
				((Flow_statementContext)_localctx).content =  ((Flow_statementContext)_localctx).while_statement.content;
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				((Flow_statementContext)_localctx).return_statement = return_statement();
				((Flow_statementContext)_localctx).content =  ((Flow_statementContext)_localctx).return_statement.content;
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				((Flow_statementContext)_localctx).break_statement = break_statement();
				((Flow_statementContext)_localctx).content =  ((Flow_statementContext)_localctx).break_statement.content;
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(158);
				((Flow_statementContext)_localctx).continue_statement = continue_statement();
				((Flow_statementContext)_localctx).content =  ((Flow_statementContext)_localctx).continue_statement.content;
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

	public static class Return_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public ExpressionContext expression;
		public TerminalNode RETURN() { return getToken(KekParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(RETURN);
			setState(164);
			((Return_statementContext)_localctx).expression = expression();
			setState(165);
			match(EOLN);

			    ((Return_statementContext)_localctx).content =  new TemplateTranslationUnit("return $", ((Return_statementContext)_localctx).expression.content);

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

	public static class Break_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public TerminalNode BREAK() { return getToken(KekParser.BREAK, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Break_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBreak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBreak_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBreak_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Break_statementContext break_statement() throws RecognitionException {
		Break_statementContext _localctx = new Break_statementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_break_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(BREAK);
			setState(169);
			match(EOLN);

			    ((Break_statementContext)_localctx).content =  new TemplateTranslationUnit("break");

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

	public static class Continue_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public TerminalNode CONTINUE() { return getToken(KekParser.CONTINUE, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Continue_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterContinue_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitContinue_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitContinue_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continue_statementContext continue_statement() throws RecognitionException {
		Continue_statementContext _localctx = new Continue_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_continue_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(CONTINUE);
			setState(173);
			match(EOLN);

			    ((Continue_statementContext)_localctx).content =  new TemplateTranslationUnit("continue");

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

	public static class If_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public If_true_partContext if_true_part;
		public If_false_partContext if_false_part;
		public If_true_partContext if_true_part() {
			return getRuleContext(If_true_partContext.class,0);
		}
		public If_false_partContext if_false_part() {
			return getRuleContext(If_false_partContext.class,0);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIf_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			((If_statementContext)_localctx).if_true_part = if_true_part();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(177);
				((If_statementContext)_localctx).if_false_part = if_false_part();
				}
			}


			    if (((If_statementContext)_localctx).if_false_part.content != null)
			        ((If_statementContext)_localctx).content =  new TemplateTranslationUnit("$\n$", ((If_statementContext)_localctx).if_true_part.content, ((If_statementContext)_localctx).if_false_part.content);
			    else
			        ((If_statementContext)_localctx).content =  ((If_statementContext)_localctx).if_true_part.content;

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

	public static class If_true_partContext extends ParserRuleContext {
		public TranslationUnit content;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode IF() { return getToken(KekParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public If_true_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_true_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIf_true_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIf_true_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIf_true_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_true_partContext if_true_part() throws RecognitionException {
		If_true_partContext _localctx = new If_true_partContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_if_true_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(IF);
			setState(183);
			((If_true_partContext)_localctx).expression = expression();
			setState(184);
			((If_true_partContext)_localctx).block = block(null);

			    ((If_true_partContext)_localctx).content =  new TemplateTranslationUnit("if ($) {\n$\n}\n", ((If_true_partContext)_localctx).expression.content, ((If_true_partContext)_localctx).block.content);

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

	public static class If_false_partContext extends ParserRuleContext {
		public TranslationUnit content;
		public BlockContext block;
		public TerminalNode ELSE() { return getToken(KekParser.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public If_false_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_false_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIf_false_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIf_false_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIf_false_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_false_partContext if_false_part() throws RecognitionException {
		If_false_partContext _localctx = new If_false_partContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_if_false_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(ELSE);
			setState(188);
			((If_false_partContext)_localctx).block = block(null);

			    ((If_false_partContext)_localctx).content =  new TemplateTranslationUnit(" else {\n$\n}\n", ((If_false_partContext)_localctx).block.content);

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

	public static class While_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode WHILE() { return getToken(KekParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitWhile_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitWhile_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_while_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(WHILE);
			setState(192);
			((While_statementContext)_localctx).expression = expression();
			setState(193);
			((While_statementContext)_localctx).block = block(null);

			    ((While_statementContext)_localctx).content =  new TemplateTranslationUnit("while ($) {\n$\n}\n", ((While_statementContext)_localctx).expression.content, ((While_statementContext)_localctx).block.content);

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

	public static class Simple_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public Var_declarationContext var_declaration;
		public Assign_statementContext assign_statement;
		public ExpressionContext expression;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Assign_statementContext assign_statement() {
			return getRuleContext(Assign_statementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Simple_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterSimple_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitSimple_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitSimple_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_statementContext simple_statement() throws RecognitionException {
		Simple_statementContext _localctx = new Simple_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_simple_statement);
		try {
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				((Simple_statementContext)_localctx).var_declaration = var_declaration();

				    contextManager.getContext().addVar(((Simple_statementContext)_localctx).var_declaration.kekVar);

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				((Simple_statementContext)_localctx).assign_statement = assign_statement();
				((Simple_statementContext)_localctx).content =  ((Simple_statementContext)_localctx).assign_statement.content;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(202);
				((Simple_statementContext)_localctx).expression = expression();
				setState(203);
				match(EOLN);
				}
				((Simple_statementContext)_localctx).content =  ((Simple_statementContext)_localctx).expression.content;
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

	public static class Assign_statementContext extends ParserRuleContext {
		public TranslationUnit content;
		public Var_usageContext var_usage;
		public ExpressionContext expression;
		public Var_usageContext var_usage() {
			return getRuleContext(Var_usageContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(KekParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public Assign_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAssign_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAssign_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAssign_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_statementContext assign_statement() throws RecognitionException {
		Assign_statementContext _localctx = new Assign_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assign_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			((Assign_statementContext)_localctx).var_usage = var_usage();
			setState(210);
			match(ASSIGN);
			setState(211);
			((Assign_statementContext)_localctx).expression = expression();
			setState(212);
			match(EOLN);

			    ((Assign_statementContext)_localctx).content =  new TemplateTranslationUnit("$ = $", ((Assign_statementContext)_localctx).var_usage.content, ((Assign_statementContext)_localctx).expression.content);

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
		public TranslationUnit content;
		public Func_callContext func_call;
		public Or_exprContext or_expr;
		public Var_usageContext var_usage;
		public LiteralContext literal;
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public Var_usageContext var_usage() {
			return getRuleContext(Var_usageContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
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
		enterRule(_localctx, 40, RULE_expression);
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				((ExpressionContext)_localctx).func_call = func_call();
				((ExpressionContext)_localctx).content =  ((ExpressionContext)_localctx).func_call.kekFunctionCall;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				((ExpressionContext)_localctx).or_expr = or_expr();
				((ExpressionContext)_localctx).content =  ((ExpressionContext)_localctx).or_expr.content;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				((ExpressionContext)_localctx).var_usage = var_usage();
				((ExpressionContext)_localctx).content =  ((ExpressionContext)_localctx).var_usage.content;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(224);
				((ExpressionContext)_localctx).literal = literal();
				((ExpressionContext)_localctx).content =  ((ExpressionContext)_localctx).literal.content;
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

	public static class Var_usageContext extends ParserRuleContext {
		public TranslationUnit content;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public Var_usageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_usage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVar_usage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVar_usage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVar_usage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_usageContext var_usage() throws RecognitionException {
		Var_usageContext _localctx = new Var_usageContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_var_usage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(ID);

			    String varName = _localctx.getChild(0).getText();
			    ((Var_usageContext)_localctx).content =  new TemplateTranslationUnit(varName);
			    contextManager.getContext().ensureVar(varName);

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

	public static class Or_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public And_exprContext and_expr;
		public Or_expr_contContext or_expr_cont;
		public And_exprContext and_expr() {
			return getRuleContext(And_exprContext.class,0);
		}
		public Or_expr_contContext or_expr_cont() {
			return getRuleContext(Or_expr_contContext.class,0);
		}
		public Or_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOr_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOr_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOr_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_exprContext or_expr() throws RecognitionException {
		Or_exprContext _localctx = new Or_exprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_or_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			((Or_exprContext)_localctx).and_expr = and_expr();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OR) {
				{
				setState(233);
				((Or_exprContext)_localctx).or_expr_cont = or_expr_cont();
				}
			}


			    if (((Or_exprContext)_localctx).or_expr_cont.content != null) {
			        ((Or_exprContext)_localctx).content =  new TemplateTranslationUnit("$ $", ((Or_exprContext)_localctx).and_expr.content, ((Or_exprContext)_localctx).or_expr_cont.content);
			    } else {
			        ((Or_exprContext)_localctx).content =  ((Or_exprContext)_localctx).and_expr.content;
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

	public static class Or_expr_contContext extends ParserRuleContext {
		public TranslationUnit content;
		public And_exprContext and_expr;
		public Or_expr_contContext or_expr_cont;
		public TerminalNode OR() { return getToken(KekParser.OR, 0); }
		public And_exprContext and_expr() {
			return getRuleContext(And_exprContext.class,0);
		}
		public Or_expr_contContext or_expr_cont() {
			return getRuleContext(Or_expr_contContext.class,0);
		}
		public Or_expr_contContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_expr_cont; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOr_expr_cont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOr_expr_cont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOr_expr_cont(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_expr_contContext or_expr_cont() throws RecognitionException {
		Or_expr_contContext _localctx = new Or_expr_contContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_or_expr_cont);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(OR);
			setState(239);
			((Or_expr_contContext)_localctx).and_expr = and_expr();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OR) {
				{
				setState(240);
				((Or_expr_contContext)_localctx).or_expr_cont = or_expr_cont();
				}
			}


			    if (((Or_expr_contContext)_localctx).or_expr_cont.content != null) {
			        ((Or_expr_contContext)_localctx).content =  new TemplateTranslationUnit("|| $ $", ((Or_expr_contContext)_localctx).and_expr.content, ((Or_expr_contContext)_localctx).or_expr_cont.content);
			    } else {
			        ((Or_expr_contContext)_localctx).content =  new TemplateTranslationUnit("|| $", ((Or_expr_contContext)_localctx).and_expr.content);
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

	public static class And_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public Not_exprContext not_expr;
		public And_expr_contContext and_expr_cont;
		public Not_exprContext not_expr() {
			return getRuleContext(Not_exprContext.class,0);
		}
		public And_expr_contContext and_expr_cont() {
			return getRuleContext(And_expr_contContext.class,0);
		}
		public And_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAnd_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAnd_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAnd_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_exprContext and_expr() throws RecognitionException {
		And_exprContext _localctx = new And_exprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_and_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			((And_exprContext)_localctx).not_expr = not_expr();
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(246);
				((And_exprContext)_localctx).and_expr_cont = and_expr_cont();
				}
			}


			    if (((And_exprContext)_localctx).and_expr_cont.content != null) {
			        ((And_exprContext)_localctx).content =  new TemplateTranslationUnit("$ $", ((And_exprContext)_localctx).not_expr.content, ((And_exprContext)_localctx).and_expr_cont.content);
			    } else {
			        ((And_exprContext)_localctx).content =  ((And_exprContext)_localctx).not_expr.content;
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

	public static class And_expr_contContext extends ParserRuleContext {
		public TranslationUnit content;
		public Not_exprContext not_expr;
		public And_expr_contContext and_expr_cont;
		public TerminalNode AND() { return getToken(KekParser.AND, 0); }
		public Not_exprContext not_expr() {
			return getRuleContext(Not_exprContext.class,0);
		}
		public And_expr_contContext and_expr_cont() {
			return getRuleContext(And_expr_contContext.class,0);
		}
		public And_expr_contContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr_cont; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAnd_expr_cont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAnd_expr_cont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAnd_expr_cont(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_expr_contContext and_expr_cont() throws RecognitionException {
		And_expr_contContext _localctx = new And_expr_contContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_and_expr_cont);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(AND);
			setState(252);
			((And_expr_contContext)_localctx).not_expr = not_expr();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(253);
				((And_expr_contContext)_localctx).and_expr_cont = and_expr_cont();
				}
			}


			    if (((And_expr_contContext)_localctx).and_expr_cont.content != null) {
			        ((And_expr_contContext)_localctx).content =  new TemplateTranslationUnit("&& $ $", ((And_expr_contContext)_localctx).not_expr.content, ((And_expr_contContext)_localctx).and_expr_cont.content);
			    } else {
			        ((And_expr_contContext)_localctx).content =  new TemplateTranslationUnit("&& $", ((And_expr_contContext)_localctx).not_expr.content);
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

	public static class Not_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public Additive_exprContext additive_expr;
		public Not_expr_contContext not_expr_cont;
		public Additive_exprContext additive_expr() {
			return getRuleContext(Additive_exprContext.class,0);
		}
		public Not_expr_contContext not_expr_cont() {
			return getRuleContext(Not_expr_contContext.class,0);
		}
		public Not_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNot_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNot_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNot_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_exprContext not_expr() throws RecognitionException {
		Not_exprContext _localctx = new Not_exprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_not_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			((Not_exprContext)_localctx).additive_expr = additive_expr();
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(259);
				((Not_exprContext)_localctx).not_expr_cont = not_expr_cont();
				}
			}


			    if (((Not_exprContext)_localctx).not_expr_cont.content != null) {
			        ((Not_exprContext)_localctx).content =  new TemplateTranslationUnit("$ $", ((Not_exprContext)_localctx).additive_expr.content, ((Not_exprContext)_localctx).not_expr_cont.content);
			    } else {
			        ((Not_exprContext)_localctx).content =  ((Not_exprContext)_localctx).additive_expr.content;
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

	public static class Not_expr_contContext extends ParserRuleContext {
		public TranslationUnit content;
		public Additive_exprContext additive_expr;
		public Not_expr_contContext not_expr_cont;
		public TerminalNode NOT() { return getToken(KekParser.NOT, 0); }
		public Additive_exprContext additive_expr() {
			return getRuleContext(Additive_exprContext.class,0);
		}
		public Not_expr_contContext not_expr_cont() {
			return getRuleContext(Not_expr_contContext.class,0);
		}
		public Not_expr_contContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_expr_cont; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNot_expr_cont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNot_expr_cont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNot_expr_cont(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_expr_contContext not_expr_cont() throws RecognitionException {
		Not_expr_contContext _localctx = new Not_expr_contContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_not_expr_cont);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(NOT);
			setState(265);
			((Not_expr_contContext)_localctx).additive_expr = additive_expr();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(266);
				((Not_expr_contContext)_localctx).not_expr_cont = not_expr_cont();
				}
			}


			    if (((Not_expr_contContext)_localctx).not_expr_cont.content != null) {
			        ((Not_expr_contContext)_localctx).content =  new TemplateTranslationUnit("! $ $", ((Not_expr_contContext)_localctx).additive_expr.content, ((Not_expr_contContext)_localctx).not_expr_cont.content);
			    } else {
			        ((Not_expr_contContext)_localctx).content =  new TemplateTranslationUnit("! $", ((Not_expr_contContext)_localctx).additive_expr.content);
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

	public static class Additive_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public Multiplicative_exprContext multiplicative_expr;
		public Additive_expr_contContext additive_expr_cont;
		public Multiplicative_exprContext multiplicative_expr() {
			return getRuleContext(Multiplicative_exprContext.class,0);
		}
		public Additive_expr_contContext additive_expr_cont() {
			return getRuleContext(Additive_expr_contContext.class,0);
		}
		public Additive_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAdditive_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAdditive_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAdditive_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Additive_exprContext additive_expr() throws RecognitionException {
		Additive_exprContext _localctx = new Additive_exprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additive_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			((Additive_exprContext)_localctx).multiplicative_expr = multiplicative_expr();
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS || _la==PLUS) {
				{
				setState(272);
				((Additive_exprContext)_localctx).additive_expr_cont = additive_expr_cont();
				}
			}


			    if (((Additive_exprContext)_localctx).additive_expr_cont.content != null) {
			        ((Additive_exprContext)_localctx).content =  new TemplateTranslationUnit("$ $", ((Additive_exprContext)_localctx).multiplicative_expr.content, ((Additive_exprContext)_localctx).additive_expr_cont.content);
			    } else {
			        ((Additive_exprContext)_localctx).content =  ((Additive_exprContext)_localctx).multiplicative_expr.content;
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

	public static class Additive_expr_contContext extends ParserRuleContext {
		public TranslationUnit content;
		public Multiplicative_exprContext multiplicative_expr;
		public Additive_expr_contContext additive_expr_cont;
		public TerminalNode PLUS() { return getToken(KekParser.PLUS, 0); }
		public Multiplicative_exprContext multiplicative_expr() {
			return getRuleContext(Multiplicative_exprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(KekParser.MINUS, 0); }
		public Additive_expr_contContext additive_expr_cont() {
			return getRuleContext(Additive_expr_contContext.class,0);
		}
		public Additive_expr_contContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_expr_cont; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAdditive_expr_cont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAdditive_expr_cont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAdditive_expr_cont(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Additive_expr_contContext additive_expr_cont() throws RecognitionException {
		Additive_expr_contContext _localctx = new Additive_expr_contContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_additive_expr_cont);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				{
				{
				setState(277);
				match(PLUS);
				setState(278);
				((Additive_expr_contContext)_localctx).multiplicative_expr = multiplicative_expr();
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==PLUS) {
					{
					setState(279);
					((Additive_expr_contContext)_localctx).additive_expr_cont = additive_expr_cont();
					}
				}

				}
				}
				break;
			case MINUS:
				{
				{
				setState(282);
				match(MINUS);
				setState(283);
				((Additive_expr_contContext)_localctx).multiplicative_expr = multiplicative_expr();
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==PLUS) {
					{
					setState(284);
					((Additive_expr_contContext)_localctx).additive_expr_cont = additive_expr_cont();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			         if (((Additive_expr_contContext)_localctx).additive_expr_cont.content != null) {
			            ((Additive_expr_contContext)_localctx).content =  new TemplateTranslationUnit(
			                "$ $ $",
			                new TemplateTranslationUnit(_localctx.getChild(0).getText()),
			                ((Additive_expr_contContext)_localctx).multiplicative_expr.content,
			                ((Additive_expr_contContext)_localctx).additive_expr_cont.content
			            );
			        } else {
			           ((Additive_expr_contContext)_localctx).content =  new TemplateTranslationUnit(
			               "$ $",
			               new TemplateTranslationUnit(_localctx.getChild(0).getText()),
			               ((Additive_expr_contContext)_localctx).multiplicative_expr.content
			           );
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

	public static class Multiplicative_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public Terminal_exprContext terminal_expr;
		public Multiplicative_expr_contContext multiplicative_expr_cont;
		public Terminal_exprContext terminal_expr() {
			return getRuleContext(Terminal_exprContext.class,0);
		}
		public Multiplicative_expr_contContext multiplicative_expr_cont() {
			return getRuleContext(Multiplicative_expr_contContext.class,0);
		}
		public Multiplicative_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMultiplicative_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMultiplicative_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMultiplicative_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiplicative_exprContext multiplicative_expr() throws RecognitionException {
		Multiplicative_exprContext _localctx = new Multiplicative_exprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_multiplicative_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			((Multiplicative_exprContext)_localctx).terminal_expr = terminal_expr();
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD) | (1L << DIV) | (1L << MUL))) != 0)) {
				{
				setState(292);
				((Multiplicative_exprContext)_localctx).multiplicative_expr_cont = multiplicative_expr_cont();
				}
			}


			    if (((Multiplicative_exprContext)_localctx).multiplicative_expr_cont.content != null) {
			        ((Multiplicative_exprContext)_localctx).content =  new TemplateTranslationUnit("$ $", ((Multiplicative_exprContext)_localctx).terminal_expr.content, ((Multiplicative_exprContext)_localctx).multiplicative_expr_cont.content);
			    } else {
			        ((Multiplicative_exprContext)_localctx).content =  ((Multiplicative_exprContext)_localctx).terminal_expr.content;
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

	public static class Multiplicative_expr_contContext extends ParserRuleContext {
		public TranslationUnit content;
		public Terminal_exprContext terminal_expr;
		public Multiplicative_expr_contContext multiplicative_expr_cont;
		public TerminalNode MUL() { return getToken(KekParser.MUL, 0); }
		public Terminal_exprContext terminal_expr() {
			return getRuleContext(Terminal_exprContext.class,0);
		}
		public TerminalNode DIV() { return getToken(KekParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(KekParser.MOD, 0); }
		public Multiplicative_expr_contContext multiplicative_expr_cont() {
			return getRuleContext(Multiplicative_expr_contContext.class,0);
		}
		public Multiplicative_expr_contContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_expr_cont; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMultiplicative_expr_cont(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMultiplicative_expr_cont(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMultiplicative_expr_cont(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiplicative_expr_contContext multiplicative_expr_cont() throws RecognitionException {
		Multiplicative_expr_contContext _localctx = new Multiplicative_expr_contContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_multiplicative_expr_cont);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MUL:
				{
				{
				setState(297);
				match(MUL);
				setState(298);
				((Multiplicative_expr_contContext)_localctx).terminal_expr = terminal_expr();
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD) | (1L << DIV) | (1L << MUL))) != 0)) {
					{
					setState(299);
					((Multiplicative_expr_contContext)_localctx).multiplicative_expr_cont = multiplicative_expr_cont();
					}
				}

				}
				}
				break;
			case DIV:
				{
				{
				setState(302);
				match(DIV);
				setState(303);
				((Multiplicative_expr_contContext)_localctx).terminal_expr = terminal_expr();
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD) | (1L << DIV) | (1L << MUL))) != 0)) {
					{
					setState(304);
					((Multiplicative_expr_contContext)_localctx).multiplicative_expr_cont = multiplicative_expr_cont();
					}
				}

				}
				}
				break;
			case MOD:
				{
				{
				setState(307);
				match(MOD);
				setState(308);
				((Multiplicative_expr_contContext)_localctx).terminal_expr = terminal_expr();
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD) | (1L << DIV) | (1L << MUL))) != 0)) {
					{
					setState(309);
					((Multiplicative_expr_contContext)_localctx).multiplicative_expr_cont = multiplicative_expr_cont();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			        if (((Multiplicative_expr_contContext)_localctx).multiplicative_expr_cont.content != null) {
			            ((Multiplicative_expr_contContext)_localctx).content =  new TemplateTranslationUnit(
			                "$ $ $",
			                new TemplateTranslationUnit(_localctx.getChild(0).getText()),
			                ((Multiplicative_expr_contContext)_localctx).terminal_expr.content,
			                ((Multiplicative_expr_contContext)_localctx).multiplicative_expr_cont.content
			            );
			        } else {
			           ((Multiplicative_expr_contContext)_localctx).content =  new TemplateTranslationUnit(
			               "$ $",
			               new TemplateTranslationUnit(_localctx.getChild(0).getText()),
			               ((Multiplicative_expr_contContext)_localctx).terminal_expr.content
			           );
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

	public static class Terminal_exprContext extends ParserRuleContext {
		public TranslationUnit content;
		public ExpressionContext expression;
		public Func_callContext func_call;
		public Var_usageContext var_usage;
		public LiteralContext literal;
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Var_usageContext var_usage() {
			return getRuleContext(Var_usageContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Terminal_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterTerminal_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitTerminal_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitTerminal_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Terminal_exprContext terminal_expr() throws RecognitionException {
		Terminal_exprContext _localctx = new Terminal_exprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_terminal_expr);
		try {
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(316);
				match(LPAR);
				setState(317);
				((Terminal_exprContext)_localctx).expression = expression();
				setState(318);
				match(RPAR);
				((Terminal_exprContext)_localctx).content =  ((Terminal_exprContext)_localctx).expression.content;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				((Terminal_exprContext)_localctx).func_call = func_call();
				((Terminal_exprContext)_localctx).content =  ((Terminal_exprContext)_localctx).func_call.kekFunctionCall;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(324);
				((Terminal_exprContext)_localctx).var_usage = var_usage();
				((Terminal_exprContext)_localctx).content =  ((Terminal_exprContext)_localctx).var_usage.content;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(327);
				((Terminal_exprContext)_localctx).literal = literal();
				((Terminal_exprContext)_localctx).content =  ((Terminal_exprContext)_localctx).literal.content;
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

	public static class Func_callContext extends ParserRuleContext {
		public KekFunctionCall kekFunctionCall;
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
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
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_func_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(ID);
			setState(333);
			match(LPAR);
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << BOOL) | (1L << INT) | (1L << ID))) != 0)) {
				{
				setState(334);
				((Func_callContext)_localctx).expression = expression();
				((Func_callContext)_localctx).exprs.add(((Func_callContext)_localctx).expression);
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(335);
					match(COMMA);
					setState(336);
					((Func_callContext)_localctx).expression = expression();
					((Func_callContext)_localctx).exprs.add(((Func_callContext)_localctx).expression);
					}
					}
					setState(341);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(344);
			match(RPAR);

			    String funcName = _localctx.getChild(0).getText();
			    List<TranslationUnit> args = ((Func_callContext)_localctx).exprs.stream().map(x -> x.content).collect(Collectors.toList());
			    contextManager.getGlobalContext().ensureFunction(funcName, args);
			    ((Func_callContext)_localctx).kekFunctionCall =  new KekFunctionCall(funcName, args);

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
		public TranslationUnit content;
		public Int_literalContext int_literal;
		public Bool_literalContext bool_literal;
		public Int_literalContext int_literal() {
			return getRuleContext(Int_literalContext.class,0);
		}
		public Bool_literalContext bool_literal() {
			return getRuleContext(Bool_literalContext.class,0);
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
		enterRule(_localctx, 68, RULE_literal);
		try {
			setState(353);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				((LiteralContext)_localctx).int_literal = int_literal();
				((LiteralContext)_localctx).content =  ((LiteralContext)_localctx).int_literal.content;
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				((LiteralContext)_localctx).bool_literal = bool_literal();
				((LiteralContext)_localctx).content =  ((LiteralContext)_localctx).bool_literal.content;
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

	public static class Int_literalContext extends ParserRuleContext {
		public TranslationUnit content;
		public TerminalNode INT() { return getToken(KekParser.INT, 0); }
		public Int_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterInt_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitInt_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitInt_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_literalContext int_literal() throws RecognitionException {
		Int_literalContext _localctx = new Int_literalContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_int_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(INT);

			    ((Int_literalContext)_localctx).content =  new TemplateTranslationUnit(_localctx.getChild(0).getText());

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

	public static class Bool_literalContext extends ParserRuleContext {
		public TranslationUnit content;
		public TerminalNode BOOL() { return getToken(KekParser.BOOL, 0); }
		public Bool_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBool_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBool_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBool_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_literalContext bool_literal() throws RecognitionException {
		Bool_literalContext _localctx = new Bool_literalContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_bool_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(BOOL);

			    ((Bool_literalContext)_localctx).content =  new TemplateTranslationUnit(_localctx.getChild(0).getText());

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u016c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\6\2P\n\2\r\2\16\2Q\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\7\5h\n\5\f\5\16\5k\13\5\5\5m\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\5\t\177\n\t\3\n\3\n\3\n\7\n\u0084\n"+
		"\n\f\n\16\n\u0087\13\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u0093\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\5\f\u00a4\n\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\5\20\u00b5\n\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00d2\n\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26"+
		"\u00e6\n\26\3\27\3\27\3\27\3\30\3\30\5\30\u00ed\n\30\3\30\3\30\3\31\3"+
		"\31\3\31\5\31\u00f4\n\31\3\31\3\31\3\32\3\32\5\32\u00fa\n\32\3\32\3\32"+
		"\3\33\3\33\3\33\5\33\u0101\n\33\3\33\3\33\3\34\3\34\5\34\u0107\n\34\3"+
		"\34\3\34\3\35\3\35\3\35\5\35\u010e\n\35\3\35\3\35\3\36\3\36\5\36\u0114"+
		"\n\36\3\36\3\36\3\37\3\37\3\37\5\37\u011b\n\37\3\37\3\37\3\37\5\37\u0120"+
		"\n\37\5\37\u0122\n\37\3\37\3\37\3 \3 \5 \u0128\n \3 \3 \3!\3!\3!\5!\u012f"+
		"\n!\3!\3!\3!\5!\u0134\n!\3!\3!\3!\5!\u0139\n!\5!\u013b\n!\3!\3!\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u014d\n\"\3#\3"+
		"#\3#\3#\3#\7#\u0154\n#\f#\16#\u0157\13#\5#\u0159\n#\3#\3#\3#\3$\3$\3$"+
		"\3$\3$\3$\5$\u0164\n$\3%\3%\3%\3&\3&\3&\3&\2\2\'\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\2\2\u016d\2L\3\2\2"+
		"\2\4U\3\2\2\2\6[\3\2\2\2\bl\3\2\2\2\np\3\2\2\2\ft\3\2\2\2\16x\3\2\2\2"+
		"\20~\3\2\2\2\22\u0080\3\2\2\2\24\u0092\3\2\2\2\26\u00a3\3\2\2\2\30\u00a5"+
		"\3\2\2\2\32\u00aa\3\2\2\2\34\u00ae\3\2\2\2\36\u00b2\3\2\2\2 \u00b8\3\2"+
		"\2\2\"\u00bd\3\2\2\2$\u00c1\3\2\2\2&\u00d1\3\2\2\2(\u00d3\3\2\2\2*\u00e5"+
		"\3\2\2\2,\u00e7\3\2\2\2.\u00ea\3\2\2\2\60\u00f0\3\2\2\2\62\u00f7\3\2\2"+
		"\2\64\u00fd\3\2\2\2\66\u0104\3\2\2\28\u010a\3\2\2\2:\u0111\3\2\2\2<\u0121"+
		"\3\2\2\2>\u0125\3\2\2\2@\u013a\3\2\2\2B\u014c\3\2\2\2D\u014e\3\2\2\2F"+
		"\u0163\3\2\2\2H\u0165\3\2\2\2J\u0168\3\2\2\2LO\b\2\1\2MP\5\4\3\2NP\5\6"+
		"\4\2OM\3\2\2\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\b\2"+
		"\1\2T\3\3\2\2\2UV\7\24\2\2VW\7\36\2\2WX\5\f\7\2XY\7\"\2\2YZ\b\3\1\2Z\5"+
		"\3\2\2\2[\\\7\25\2\2\\]\7\36\2\2]^\7\26\2\2^_\5\b\5\2_`\7\27\2\2`a\5\f"+
		"\7\2ab\5\22\n\2bc\b\4\1\2c\7\3\2\2\2di\5\n\6\2ef\7\32\2\2fh\5\n\6\2ge"+
		"\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jm\3\2\2\2ki\3\2\2\2ld\3\2\2\2l"+
		"m\3\2\2\2mn\3\2\2\2no\b\5\1\2o\t\3\2\2\2pq\7\36\2\2qr\5\f\7\2rs\b\6\1"+
		"\2s\13\3\2\2\2tu\7\5\2\2uv\5\16\b\2vw\b\7\1\2w\r\3\2\2\2xy\5\20\t\2yz"+
		"\b\b\1\2z\17\3\2\2\2{\177\7\3\2\2|}\7\4\2\2}\177\b\t\1\2~{\3\2\2\2~|\3"+
		"\2\2\2\177\21\3\2\2\2\u0080\u0081\7\30\2\2\u0081\u0085\7\"\2\2\u0082\u0084"+
		"\5\24\13\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089"+
		"\7\31\2\2\u0089\u008a\7\"\2\2\u008a\u008b\b\n\1\2\u008b\23\3\2\2\2\u008c"+
		"\u008d\5&\24\2\u008d\u008e\b\13\1\2\u008e\u0093\3\2\2\2\u008f\u0090\5"+
		"\26\f\2\u0090\u0091\b\13\1\2\u0091\u0093\3\2\2\2\u0092\u008c\3\2\2\2\u0092"+
		"\u008f\3\2\2\2\u0093\25\3\2\2\2\u0094\u0095\5\36\20\2\u0095\u0096\b\f"+
		"\1\2\u0096\u00a4\3\2\2\2\u0097\u0098\5$\23\2\u0098\u0099\b\f\1\2\u0099"+
		"\u00a4\3\2\2\2\u009a\u009b\5\30\r\2\u009b\u009c\b\f\1\2\u009c\u00a4\3"+
		"\2\2\2\u009d\u009e\5\32\16\2\u009e\u009f\b\f\1\2\u009f\u00a4\3\2\2\2\u00a0"+
		"\u00a1\5\34\17\2\u00a1\u00a2\b\f\1\2\u00a2\u00a4\3\2\2\2\u00a3\u0094\3"+
		"\2\2\2\u00a3\u0097\3\2\2\2\u00a3\u009a\3\2\2\2\u00a3\u009d\3\2\2\2\u00a3"+
		"\u00a0\3\2\2\2\u00a4\27\3\2\2\2\u00a5\u00a6\7\6\2\2\u00a6\u00a7\5*\26"+
		"\2\u00a7\u00a8\7\"\2\2\u00a8\u00a9\b\r\1\2\u00a9\31\3\2\2\2\u00aa\u00ab"+
		"\7\b\2\2\u00ab\u00ac\7\"\2\2\u00ac\u00ad\b\16\1\2\u00ad\33\3\2\2\2\u00ae"+
		"\u00af\7\7\2\2\u00af\u00b0\7\"\2\2\u00b0\u00b1\b\17\1\2\u00b1\35\3\2\2"+
		"\2\u00b2\u00b4\5 \21\2\u00b3\u00b5\5\"\22\2\u00b4\u00b3\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\20\1\2\u00b7\37\3\2\2"+
		"\2\u00b8\u00b9\7\n\2\2\u00b9\u00ba\5*\26\2\u00ba\u00bb\5\22\n\2\u00bb"+
		"\u00bc\b\21\1\2\u00bc!\3\2\2\2\u00bd\u00be\7\t\2\2\u00be\u00bf\5\22\n"+
		"\2\u00bf\u00c0\b\22\1\2\u00c0#\3\2\2\2\u00c1\u00c2\7\13\2\2\u00c2\u00c3"+
		"\5*\26\2\u00c3\u00c4\5\22\n\2\u00c4\u00c5\b\23\1\2\u00c5%\3\2\2\2\u00c6"+
		"\u00c7\5\4\3\2\u00c7\u00c8\b\24\1\2\u00c8\u00d2\3\2\2\2\u00c9\u00ca\5"+
		"(\25\2\u00ca\u00cb\b\24\1\2\u00cb\u00d2\3\2\2\2\u00cc\u00cd\5*\26\2\u00cd"+
		"\u00ce\7\"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b\24\1\2\u00d0\u00d2\3"+
		"\2\2\2\u00d1\u00c6\3\2\2\2\u00d1\u00c9\3\2\2\2\u00d1\u00cc\3\2\2\2\u00d2"+
		"\'\3\2\2\2\u00d3\u00d4\5,\27\2\u00d4\u00d5\7\33\2\2\u00d5\u00d6\5*\26"+
		"\2\u00d6\u00d7\7\"\2\2\u00d7\u00d8\b\25\1\2\u00d8)\3\2\2\2\u00d9\u00da"+
		"\5D#\2\u00da\u00db\b\26\1\2\u00db\u00e6\3\2\2\2\u00dc\u00dd\5.\30\2\u00dd"+
		"\u00de\b\26\1\2\u00de\u00e6\3\2\2\2\u00df\u00e0\5,\27\2\u00e0\u00e1\b"+
		"\26\1\2\u00e1\u00e6\3\2\2\2\u00e2\u00e3\5F$\2\u00e3\u00e4\b\26\1\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00d9\3\2\2\2\u00e5\u00dc\3\2\2\2\u00e5\u00df\3\2"+
		"\2\2\u00e5\u00e2\3\2\2\2\u00e6+\3\2\2\2\u00e7\u00e8\7\36\2\2\u00e8\u00e9"+
		"\b\27\1\2\u00e9-\3\2\2\2\u00ea\u00ec\5\62\32\2\u00eb\u00ed\5\60\31\2\u00ec"+
		"\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\b\30"+
		"\1\2\u00ef/\3\2\2\2\u00f0\u00f1\7\23\2\2\u00f1\u00f3\5\62\32\2\u00f2\u00f4"+
		"\5\60\31\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2"+
		"\u00f5\u00f6\b\31\1\2\u00f6\61\3\2\2\2\u00f7\u00f9\5\66\34\2\u00f8\u00fa"+
		"\5\64\33\2\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2"+
		"\u00fb\u00fc\b\32\1\2\u00fc\63\3\2\2\2\u00fd\u00fe\7\22\2\2\u00fe\u0100"+
		"\5\66\34\2\u00ff\u0101\5\64\33\2\u0100\u00ff\3\2\2\2\u0100\u0101\3\2\2"+
		"\2\u0101\u0102\3\2\2\2\u0102\u0103\b\33\1\2\u0103\65\3\2\2\2\u0104\u0106"+
		"\5:\36\2\u0105\u0107\58\35\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0109\b\34\1\2\u0109\67\3\2\2\2\u010a\u010b\7\21"+
		"\2\2\u010b\u010d\5:\36\2\u010c\u010e\58\35\2\u010d\u010c\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\b\35\1\2\u01109\3\2\2\2"+
		"\u0111\u0113\5> \2\u0112\u0114\5<\37\2\u0113\u0112\3\2\2\2\u0113\u0114"+
		"\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\b\36\1\2\u0116;\3\2\2\2\u0117"+
		"\u0118\7\20\2\2\u0118\u011a\5> \2\u0119\u011b\5<\37\2\u011a\u0119\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\u0122\3\2\2\2\u011c\u011d\7\17\2\2\u011d"+
		"\u011f\5> \2\u011e\u0120\5<\37\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2"+
		"\2\u0120\u0122\3\2\2\2\u0121\u0117\3\2\2\2\u0121\u011c\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0124\b\37\1\2\u0124=\3\2\2\2\u0125\u0127\5B\"\2\u0126"+
		"\u0128\5@!\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2"+
		"\2\u0129\u012a\b \1\2\u012a?\3\2\2\2\u012b\u012c\7\16\2\2\u012c\u012e"+
		"\5B\"\2\u012d\u012f\5@!\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u013b\3\2\2\2\u0130\u0131\7\r\2\2\u0131\u0133\5B\"\2\u0132\u0134\5@!"+
		"\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u013b\3\2\2\2\u0135\u0136"+
		"\7\f\2\2\u0136\u0138\5B\"\2\u0137\u0139\5@!\2\u0138\u0137\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u012b\3\2\2\2\u013a\u0130\3\2"+
		"\2\2\u013a\u0135\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\b!\1\2\u013d"+
		"A\3\2\2\2\u013e\u013f\7\26\2\2\u013f\u0140\5*\26\2\u0140\u0141\7\27\2"+
		"\2\u0141\u0142\b\"\1\2\u0142\u014d\3\2\2\2\u0143\u0144\5D#\2\u0144\u0145"+
		"\b\"\1\2\u0145\u014d\3\2\2\2\u0146\u0147\5,\27\2\u0147\u0148\b\"\1\2\u0148"+
		"\u014d\3\2\2\2\u0149\u014a\5F$\2\u014a\u014b\b\"\1\2\u014b\u014d\3\2\2"+
		"\2\u014c\u013e\3\2\2\2\u014c\u0143\3\2\2\2\u014c\u0146\3\2\2\2\u014c\u0149"+
		"\3\2\2\2\u014dC\3\2\2\2\u014e\u014f\7\36\2\2\u014f\u0158\7\26\2\2\u0150"+
		"\u0155\5*\26\2\u0151\u0152\7\32\2\2\u0152\u0154\5*\26\2\u0153\u0151\3"+
		"\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0150\3\2\2\2\u0158\u0159\3\2"+
		"\2\2\u0159\u015a\3\2\2\2\u015a\u015b\7\27\2\2\u015b\u015c\b#\1\2\u015c"+
		"E\3\2\2\2\u015d\u015e\5H%\2\u015e\u015f\b$\1\2\u015f\u0164\3\2\2\2\u0160"+
		"\u0161\5J&\2\u0161\u0162\b$\1\2\u0162\u0164\3\2\2\2\u0163\u015d\3\2\2"+
		"\2\u0163\u0160\3\2\2\2\u0164G\3\2\2\2\u0165\u0166\7\35\2\2\u0166\u0167"+
		"\b%\1\2\u0167I\3\2\2\2\u0168\u0169\7\34\2\2\u0169\u016a\b&\1\2\u016aK"+
		"\3\2\2\2 OQil~\u0085\u0092\u00a3\u00b4\u00d1\u00e5\u00ec\u00f3\u00f9\u0100"+
		"\u0106\u010d\u0113\u011a\u011f\u0121\u0127\u012e\u0133\u0138\u013a\u014c"+
		"\u0155\u0158\u0163";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
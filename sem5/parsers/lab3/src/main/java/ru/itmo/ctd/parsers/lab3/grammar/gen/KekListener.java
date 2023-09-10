// Generated from C:/Programing/sem5/parsers/lab3/src/main/java/ru/itmo/ctd/parsers/lab3/grammar\Kek.g4 by ANTLR 4.9.2
package ru.itmo.ctd.parsers.lab3.grammar.gen;

    import ru.itmo.ctd.parsers.lab3.translation.*;
    import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KekParser}.
 */
public interface KekListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(KekParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(KekParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(KekParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(KekParser.Var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#func_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunc_declaration(KekParser.Func_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#func_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunc_declaration(KekParser.Func_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#func_args}.
	 * @param ctx the parse tree
	 */
	void enterFunc_args(KekParser.Func_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#func_args}.
	 * @param ctx the parse tree
	 */
	void exitFunc_args(KekParser.Func_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunc_arg(KekParser.Func_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunc_arg(KekParser.Func_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#type_annotation}.
	 * @param ctx the parse tree
	 */
	void enterType_annotation(KekParser.Type_annotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#type_annotation}.
	 * @param ctx the parse tree
	 */
	void exitType_annotation(KekParser.Type_annotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(KekParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(KekParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive_type(KekParser.Primitive_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive_type(KekParser.Primitive_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(KekParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(KekParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(KekParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(KekParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#flow_statement}.
	 * @param ctx the parse tree
	 */
	void enterFlow_statement(KekParser.Flow_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#flow_statement}.
	 * @param ctx the parse tree
	 */
	void exitFlow_statement(KekParser.Flow_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(KekParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(KekParser.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void enterBreak_statement(KekParser.Break_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void exitBreak_statement(KekParser.Break_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void enterContinue_statement(KekParser.Continue_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void exitContinue_statement(KekParser.Continue_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(KekParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(KekParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#if_true_part}.
	 * @param ctx the parse tree
	 */
	void enterIf_true_part(KekParser.If_true_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#if_true_part}.
	 * @param ctx the parse tree
	 */
	void exitIf_true_part(KekParser.If_true_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#if_false_part}.
	 * @param ctx the parse tree
	 */
	void enterIf_false_part(KekParser.If_false_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#if_false_part}.
	 * @param ctx the parse tree
	 */
	void exitIf_false_part(KekParser.If_false_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(KekParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(KekParser.While_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#simple_statement}.
	 * @param ctx the parse tree
	 */
	void enterSimple_statement(KekParser.Simple_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#simple_statement}.
	 * @param ctx the parse tree
	 */
	void exitSimple_statement(KekParser.Simple_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssign_statement(KekParser.Assign_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#assign_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssign_statement(KekParser.Assign_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(KekParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(KekParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#var_usage}.
	 * @param ctx the parse tree
	 */
	void enterVar_usage(KekParser.Var_usageContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#var_usage}.
	 * @param ctx the parse tree
	 */
	void exitVar_usage(KekParser.Var_usageContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#or_expr}.
	 * @param ctx the parse tree
	 */
	void enterOr_expr(KekParser.Or_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#or_expr}.
	 * @param ctx the parse tree
	 */
	void exitOr_expr(KekParser.Or_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#or_expr_cont}.
	 * @param ctx the parse tree
	 */
	void enterOr_expr_cont(KekParser.Or_expr_contContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#or_expr_cont}.
	 * @param ctx the parse tree
	 */
	void exitOr_expr_cont(KekParser.Or_expr_contContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#and_expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expr(KekParser.And_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#and_expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expr(KekParser.And_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#and_expr_cont}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expr_cont(KekParser.And_expr_contContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#and_expr_cont}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expr_cont(KekParser.And_expr_contContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#not_expr}.
	 * @param ctx the parse tree
	 */
	void enterNot_expr(KekParser.Not_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#not_expr}.
	 * @param ctx the parse tree
	 */
	void exitNot_expr(KekParser.Not_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#not_expr_cont}.
	 * @param ctx the parse tree
	 */
	void enterNot_expr_cont(KekParser.Not_expr_contContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#not_expr_cont}.
	 * @param ctx the parse tree
	 */
	void exitNot_expr_cont(KekParser.Not_expr_contContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#additive_expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expr(KekParser.Additive_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#additive_expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expr(KekParser.Additive_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#additive_expr_cont}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expr_cont(KekParser.Additive_expr_contContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#additive_expr_cont}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expr_cont(KekParser.Additive_expr_contContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#multiplicative_expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expr(KekParser.Multiplicative_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#multiplicative_expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expr(KekParser.Multiplicative_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#multiplicative_expr_cont}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expr_cont(KekParser.Multiplicative_expr_contContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#multiplicative_expr_cont}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expr_cont(KekParser.Multiplicative_expr_contContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#terminal_expr}.
	 * @param ctx the parse tree
	 */
	void enterTerminal_expr(KekParser.Terminal_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#terminal_expr}.
	 * @param ctx the parse tree
	 */
	void exitTerminal_expr(KekParser.Terminal_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(KekParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(KekParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(KekParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(KekParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(KekParser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(KekParser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void enterBool_literal(KekParser.Bool_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void exitBool_literal(KekParser.Bool_literalContext ctx);
}
// Generated from C:/Programing/sem5/parsers/lab3/src/main/java/ru/itmo/ctd/parsers/lab3/grammar\Kek.g4 by ANTLR 4.9.2
package ru.itmo.ctd.parsers.lab3.grammar.gen;

    import ru.itmo.ctd.parsers.lab3.translation.*;
    import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KekParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KekVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(KekParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration(KekParser.Var_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#func_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_declaration(KekParser.Func_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#func_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_args(KekParser.Func_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#func_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_arg(KekParser.Func_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#type_annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_annotation(KekParser.Type_annotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(KekParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#primitive_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_type(KekParser.Primitive_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(KekParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KekParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#flow_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow_statement(KekParser.Flow_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(KekParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#break_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_statement(KekParser.Break_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#continue_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_statement(KekParser.Continue_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(KekParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#if_true_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_true_part(KekParser.If_true_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#if_false_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_false_part(KekParser.If_false_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(KekParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#simple_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_statement(KekParser.Simple_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#assign_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_statement(KekParser.Assign_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(KekParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#var_usage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_usage(KekParser.Var_usageContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#or_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_expr(KekParser.Or_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#or_expr_cont}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_expr_cont(KekParser.Or_expr_contContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#and_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(KekParser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#and_expr_cont}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr_cont(KekParser.And_expr_contContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#not_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_expr(KekParser.Not_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#not_expr_cont}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_expr_cont(KekParser.Not_expr_contContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#additive_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expr(KekParser.Additive_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#additive_expr_cont}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expr_cont(KekParser.Additive_expr_contContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#multiplicative_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_expr(KekParser.Multiplicative_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#multiplicative_expr_cont}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_expr_cont(KekParser.Multiplicative_expr_contContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#terminal_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminal_expr(KekParser.Terminal_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#func_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(KekParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(KekParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#int_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_literal(KekParser.Int_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#bool_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_literal(KekParser.Bool_literalContext ctx);
}
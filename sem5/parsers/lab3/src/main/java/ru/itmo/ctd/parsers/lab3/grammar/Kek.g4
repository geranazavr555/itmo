grammar Kek;

@header {
    import ru.itmo.ctd.parsers.lab3.translation.*;
    import java.util.stream.Collectors;
}

@members {
    private KekContextManager contextManager;
}

file [KekContextManager contextManager] returns [KekFile kekFile]: {
    this.contextManager = contextManager;
} (vars+=var_declaration | funcs+=func_declaration)+ {
    //KekGlobalContext globalContext = contextManager.getGlobalContext();
    //$funcs.stream().map(x -> x.kekFunction).forEach(globalContext::addFunction);
    //$vars.stream().map(x -> x.kekVar).forEach(globalContext::addVar);
    $kekFile = new KekFile(contextManager.getGlobalContext());
};

var_declaration returns [KekVar kekVar]: LET ID type_annotation EOLN {
// ????????????????????????
    KekContext context = contextManager.getContext();
    $kekVar = new KekVar($type_annotation.kekType, _localctx.getChild(1).getText());
    context.addVar($kekVar);
};

func_declaration returns [KekFunction kekFunction]: DEF ID LPAR func_args RPAR type_annotation block[$func_args.args] {
    $kekFunction = new KekFunction(
        _localctx.getChild(1).getText(),
        $type_annotation.kekType,
        $func_args.args,
        $block.content
    );
    contextManager.getGlobalContext().addFunction($kekFunction);
};

func_args returns [List<KekVar> args]: (argContexts+=func_arg (COMMA argContexts+=func_arg)*)? {
    $args = $argContexts.stream().map(x -> x.kekVar).collect(Collectors.toList());
};

func_arg returns [KekVar kekVar]: ID type_annotation {
    $kekVar = new KekVar($type_annotation.kekType, _localctx.getChild(0).getText());
};

type_annotation returns [KekType kekType]: COLON type {$kekType = $type.kekType;};
type returns [KekType kekType]: primitive_type {$kekType = $primitive_type.kekType;};
primitive_type returns [KekType kekType]: INT_TYPE | BOOL_TYPE {
    $kekType = KekType.valueOf(_localctx.getChild(0).getText().toUpperCase());
};

block [List<KekVar> additionalVars] returns [KekBlock content]: LBRACE EOLN (stmts+=statement)* RBRACE EOLN {
    if ($additionalVars != null) {
        KekContext context = contextManager.getContext();
        $additionalVars.forEach(context::addVar);
    }
    $content = new KekBlock($stmts.stream().map(x -> x.content).collect(Collectors.toList()));
};

statement returns [TranslationUnit content]:
    simple_statement {$content = $simple_statement.content;} | flow_statement {$content = $flow_statement.content;};

flow_statement returns [TranslationUnit content]:
        if_statement {$content = $if_statement.content;} |
        while_statement {$content = $while_statement.content;} |
        return_statement {$content = $return_statement.content;} |
        break_statement {$content = $break_statement.content;} |
        continue_statement {$content = $continue_statement.content;};

return_statement returns [TranslationUnit content]: RETURN expression EOLN {
    $content = new TemplateTranslationUnit("return $", $expression.content);
};
break_statement returns [TranslationUnit content]: BREAK EOLN {
    $content = new TemplateTranslationUnit("break");
};
continue_statement returns [TranslationUnit content]: CONTINUE EOLN {
    $content = new TemplateTranslationUnit("continue");
};

if_statement returns [TranslationUnit content]: if_true_part if_false_part? {
    if ($if_false_part.content != null)
        $content = new TemplateTranslationUnit("$\n$", $if_true_part.content, $if_false_part.content);
    else
        $content = $if_true_part.content;
};
if_true_part returns [TranslationUnit content]: IF expression block[null] {
    $content = new TemplateTranslationUnit("if ($) {\n$\n}\n", $expression.content, $block.content);
};
if_false_part returns [TranslationUnit content]: ELSE block[null] {
    $content = new TemplateTranslationUnit(" else {\n$\n}\n", $block.content);
};

while_statement returns [TranslationUnit content]: WHILE expression block[null] {
    $content = new TemplateTranslationUnit("while ($) {\n$\n}\n", $expression.content, $block.content);
};

simple_statement returns [TranslationUnit content]: var_declaration {
    contextManager.getContext().addVar($var_declaration.kekVar);
} | assign_statement {$content = $assign_statement.content;} | (expression EOLN) {$content = $expression.content;};

assign_statement returns [TranslationUnit content]: var_usage ASSIGN expression EOLN {
    $content = new TemplateTranslationUnit("$ = $", $var_usage.content, $expression.content);
};

expression returns [TranslationUnit content]:
    func_call {$content = $func_call.kekFunctionCall;} |
    or_expr {$content = $or_expr.content;} |
    var_usage {$content = $var_usage.content;} |
    literal {$content = $literal.content;};

var_usage returns [TranslationUnit content]: ID {
    String varName = _localctx.getChild(0).getText();
    $content = new TemplateTranslationUnit(varName);
    contextManager.getContext().ensureVar(varName);
};

or_expr returns [TranslationUnit content]: and_expr or_expr_cont? {
    if ($or_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("$ $", $and_expr.content, $or_expr_cont.content);
    } else {
        $content = $and_expr.content;
    }
};
or_expr_cont returns [TranslationUnit content]: OR and_expr or_expr_cont? {
    if ($or_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("|| $ $", $and_expr.content, $or_expr_cont.content);
    } else {
        $content = new TemplateTranslationUnit("|| $", $and_expr.content);
    }
};
and_expr returns [TranslationUnit content]: not_expr and_expr_cont? {
    if ($and_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("$ $", $not_expr.content, $and_expr_cont.content);
    } else {
        $content = $not_expr.content;
    }
};
and_expr_cont returns [TranslationUnit content]: AND not_expr and_expr_cont? {
    if ($and_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("&& $ $", $not_expr.content, $and_expr_cont.content);
    } else {
        $content = new TemplateTranslationUnit("&& $", $not_expr.content);
    }
};
not_expr returns [TranslationUnit content]: additive_expr not_expr_cont? {
    if ($not_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("$ $", $additive_expr.content, $not_expr_cont.content);
    } else {
        $content = $additive_expr.content;
    }
};
not_expr_cont returns [TranslationUnit content]: NOT additive_expr not_expr_cont? {
    if ($not_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("! $ $", $additive_expr.content, $not_expr_cont.content);
    } else {
        $content = new TemplateTranslationUnit("! $", $additive_expr.content);
    }
};
additive_expr returns [TranslationUnit content]: multiplicative_expr additive_expr_cont? {
    if ($additive_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("$ $", $multiplicative_expr.content, $additive_expr_cont.content);
    } else {
        $content = $multiplicative_expr.content;
    }
};
additive_expr_cont returns [TranslationUnit content]:
    ((PLUS multiplicative_expr additive_expr_cont?) | (MINUS multiplicative_expr additive_expr_cont?)) {
         if ($additive_expr_cont.content != null) {
            $content = new TemplateTranslationUnit(
                "$ $ $",
                new TemplateTranslationUnit(_localctx.getChild(0).getText()),
                $multiplicative_expr.content,
                $additive_expr_cont.content
            );
        } else {
           $content = new TemplateTranslationUnit(
               "$ $",
               new TemplateTranslationUnit(_localctx.getChild(0).getText()),
               $multiplicative_expr.content
           );
        }
    };
multiplicative_expr returns [TranslationUnit content]: terminal_expr multiplicative_expr_cont? {
    if ($multiplicative_expr_cont.content != null) {
        $content = new TemplateTranslationUnit("$ $", $terminal_expr.content, $multiplicative_expr_cont.content);
    } else {
        $content = $terminal_expr.content;
    }
};
multiplicative_expr_cont returns [TranslationUnit content]:
    ((MUL terminal_expr multiplicative_expr_cont?) | (DIV terminal_expr multiplicative_expr_cont?) | (MOD terminal_expr multiplicative_expr_cont?)) {
        if ($multiplicative_expr_cont.content != null) {
            $content = new TemplateTranslationUnit(
                "$ $ $",
                new TemplateTranslationUnit(_localctx.getChild(0).getText()),
                $terminal_expr.content,
                $multiplicative_expr_cont.content
            );
        } else {
           $content = new TemplateTranslationUnit(
               "$ $",
               new TemplateTranslationUnit(_localctx.getChild(0).getText()),
               $terminal_expr.content
           );
        }
    };

terminal_expr returns [TranslationUnit content]: LPAR expression RPAR {$content = $expression.content;}
    | func_call {$content = $func_call.kekFunctionCall;} | var_usage {$content = $var_usage.content;} | literal {$content = $literal.content;};

func_call returns [KekFunctionCall kekFunctionCall]: ID LPAR (exprs+=expression (COMMA exprs+=expression)*)? RPAR {
    String funcName = _localctx.getChild(0).getText();
    List<TranslationUnit> args = $exprs.stream().map(x -> x.content).collect(Collectors.toList());
    contextManager.getGlobalContext().ensureFunction(funcName, args);
    $kekFunctionCall = new KekFunctionCall(funcName, args);
};

literal returns [TranslationUnit content]: int_literal {$content = $int_literal.content;} | bool_literal {$content = $bool_literal.content;};
int_literal returns [TranslationUnit content]: INT {
    $content = new TemplateTranslationUnit(_localctx.getChild(0).getText());
};
bool_literal returns [TranslationUnit content]: BOOL {
    $content = new TemplateTranslationUnit(_localctx.getChild(0).getText());
};

INT_TYPE: 'int';
BOOL_TYPE: 'bool';
COLON: ':';
RETURN: 'return';
CONTINUE: 'continue';
BREAK: 'break';
ELSE: 'else';
IF: 'if';
WHILE: 'while';
MOD: '%';
DIV: '/';
MUL: '*';
MINUS: '-';
PLUS: '+';
NOT: 'not' | '!';
AND: 'and' | '&';
OR: 'or' | '|';
LET: 'let';
DEF: 'def';
LPAR: '(';
RPAR: ')';
LBRACE: '{';
RBRACE: '}';
COMMA: ',';
ASSIGN: '=';

BOOL: 'true' | 'false';
INT: ('+'|'-')?[0-9]+;
ID: [A-Za-z_][A-Za-z0-9_]*;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT_1: '//' ~[\r\n]* -> skip;
LINE_COMMENT_2: '#' ~[\r\n]* -> skip;
EOLN: WS? '\n' WS?;
WS: [ \n\t\r]+ -> skip;

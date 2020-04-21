package com.evaluator.parser;

import com.evaluator.expression.Expression;
import com.evaluator.parser.token.Token;

import java.util.LinkedList;

import static com.evaluator.parser.TokenType.*;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionParser {

    private static Tokenizer tokenizer = new Tokenizer();

    static {
        tokenizer.add("POW|IF|MIN", FUNCTION);
        tokenizer.add("\\+|-", OPERATOR);
        tokenizer.add("\\*|/", OPERATOR);
        tokenizer.add("\\<=", OPERATOR);
        tokenizer.add("0?-?\\+?\\d+(\\.\\d+)?", CONSTANT);
        tokenizer.add("\"[^\"]*\"|((?=_[a-z_A-Z_0-9]|[A-Z_a-z])[A-Z_a-z_0-9]+)", VARIABLE);
    }

    public static Expression parse(String expr) {
        String postFixExpr = InfixToPostfixConverter.convert(expr);
        LinkedList<Token> tokens = tokenizer.tokenize(postFixExpr);
        return ExpressionMaker.make(tokens);
    }
}

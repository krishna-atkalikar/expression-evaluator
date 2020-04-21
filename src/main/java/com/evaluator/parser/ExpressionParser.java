package com.evaluator.parser;

import com.evaluator.expression.Expression;
import com.evaluator.parser.token.Token;

import java.util.List;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionParser {

    public static Expression parse(String expr) {
        List<Token> tokens = InfixToPostfixTokenizer.tokenize(expr);
        return ExpressionBuilder.build(tokens);
    }
}

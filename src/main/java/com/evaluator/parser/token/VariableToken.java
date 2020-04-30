package com.evaluator.parser.token;

import com.evaluator.expression.Expression;

import static com.evaluator.util.Expressions.param;

/**
 * Represents a variable token
 *
 * @author shrikrushna on 2020-04-19
 */
public class VariableToken extends Token {

    public VariableToken(String token) {
        super(token);
    }

    @Override
    public boolean isVariableToken() {
        return true;
    }

    @Override
    public Expression toExpression(Expression... expressions) {
        return param(getToken());
    }
}

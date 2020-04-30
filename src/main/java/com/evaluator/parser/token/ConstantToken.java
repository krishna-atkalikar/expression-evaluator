package com.evaluator.parser.token;

import com.evaluator.expression.Expression;

import static com.evaluator.util.Expressions.constant;

/**
 * Represents a constant token
 *
 * @author shrikrushna on 2020-04-19
 */
public class ConstantToken extends Token {

    public ConstantToken(String token) {
        super(token);
    }

    @Override
    public boolean isConstantToken() {
        return true;
    }

    @Override
    public Expression toExpression(Expression... expressions) {
        return constant(getToken());
    }
}

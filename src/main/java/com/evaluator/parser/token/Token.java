package com.evaluator.parser.token;

import com.evaluator.expression.Expression;

/**
 * Class that represents token in an expression.
 *
 * @author shrikrushna on 2020-04-19
 */
public abstract class Token {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public boolean isVariableToken() {
        return false;
    }

    public boolean isConstantToken() {
        return false;
    }

    public boolean isOperatorToken() {
        return false;
    }

    public boolean isDateToken() {
        return false;
    }

    public abstract Expression toExpression(Expression... expressions);

    @Override
    public String toString() {
        return token;
    }
}
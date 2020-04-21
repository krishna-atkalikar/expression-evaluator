package com.evaluator.parser.token;

/**
 * @author shrikrushna on 2020-04-19
 */
public class OperatorToken extends Token {

    public OperatorToken(String token) {
        super(token);
    }

    @Override
    public boolean isOperatorToken() {
        return true;
    }
}

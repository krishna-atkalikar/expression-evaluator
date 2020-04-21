package com.evaluator.parser.token;

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
}

package com.evaluator.parser.token;

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
}

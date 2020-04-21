package com.evaluator.parser.token;

/**
 * @author shrikrushna on 2020-04-19
 */
public class Token {

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
}
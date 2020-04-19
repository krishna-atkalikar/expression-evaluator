package com.evaluator.parser;

/**
 * @author shrikrushna on 2020-04-19
 */
public class Token {

    private final int sequence;
    private final String token;

    public Token(int sequence, String token) {
        this.sequence = sequence;
        this.token = token;
    }

    public int getSequence() {
        return sequence;
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
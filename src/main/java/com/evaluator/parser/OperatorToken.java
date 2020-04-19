package com.evaluator.parser;

/**
 * @author shrikrushna on 2020-04-19
 */
public class OperatorToken extends Token {

    public OperatorToken(int token, String sequence) {
        super(token, sequence);
    }

    @Override
    public boolean isOperatorToken() {
        return true;
    }
}

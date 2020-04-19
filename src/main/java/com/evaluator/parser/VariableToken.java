package com.evaluator.parser;

/**
 * @author shrikrushna on 2020-04-19
 */
public class VariableToken extends Token {

    public VariableToken(int token, String sequence) {
        super(token, sequence);
    }

    @Override
    public boolean isVariableToken() {
        return true;
    }
}

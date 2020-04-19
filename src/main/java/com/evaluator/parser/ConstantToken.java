package com.evaluator.parser;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ConstantToken extends Token {

    public ConstantToken(int token, String sequence) {
        super(token, sequence);
    }

    @Override
    public boolean isConstantToken() {
        return true;
    }
}

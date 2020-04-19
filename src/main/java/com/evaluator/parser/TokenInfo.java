package com.evaluator.parser;

import java.util.regex.Pattern;

/**
 * @author shrikrushna on 2020-04-19
 */
public class TokenInfo {

    private final Pattern regex;
    private final int sequence;
    private TokenType tokenType;

    public TokenInfo(Pattern regex, int sequence, TokenType tokenType) {
        super();
        this.regex = regex;
        this.sequence = sequence;
        this.tokenType = tokenType;
    }

    public Pattern getRegex() {
        return regex;
    }

    public int getSequence() {
        return sequence;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    @Override public String toString() {
        return "TokenInfo{" +
                "regex=" + regex +
                ", sequence=" + sequence +
                ", tokenType=" + tokenType +
                '}';
    }
}

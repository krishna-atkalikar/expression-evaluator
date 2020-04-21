package com.evaluator.parser;

import java.util.regex.Pattern;

/**
 * @author shrikrushna on 2020-04-19
 */
public class TokenInfo {

    private final Pattern regex;
    private TokenType tokenType;

    public TokenInfo(Pattern regex, TokenType tokenType) {
        this.regex = regex;
        this.tokenType = tokenType;
    }

    public Pattern getRegex() {
        return regex;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "regex=" + regex +
                ", tokenType=" + tokenType +
                '}';
    }
}

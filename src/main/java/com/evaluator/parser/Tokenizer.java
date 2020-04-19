package com.evaluator.parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shrikrushna on 2020-04-19
 */
public class Tokenizer {

    private LinkedList<TokenInfo> tokenInfos;

    public Tokenizer() {
        tokenInfos = new LinkedList<>();
    }

    public void add(String regex, int sequence, TokenType tokenType) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), sequence, tokenType));
//        tokenInfos.add(new TokenInfo(Pattern.compile(regex), sequence, tokenType));
    }

    public LinkedList<Token> tokenize(String str) {
        LinkedList<Token> tokens = new LinkedList<>();

        String s = str.trim();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.getRegex().matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    if (info.getTokenType() == TokenType.OPERATOR)
                        tokens.add(new OperatorToken(info.getSequence(), tok));
                    else if (info.getTokenType() == TokenType.CONSTANT)
                        tokens.add(new ConstantToken(info.getSequence(), tok));
                    else if (info.getTokenType() == TokenType.FUNCTION) {
                        tokens.add(new OperatorToken(info.getSequence(), tok));
                    } else if (info.getTokenType() == TokenType.VARIABLE) {
                        tokens.add(new VariableToken(info.getSequence(), tok));
                    }
                    break;
                }
            }
            if (!match)
                throw new RuntimeException("Unexpected character in input: " + s);
        }
        return tokens;
    }
}

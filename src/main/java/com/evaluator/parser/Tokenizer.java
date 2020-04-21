package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.parser.token.ConstantToken;
import com.evaluator.parser.token.OperatorToken;
import com.evaluator.parser.token.Token;
import com.evaluator.parser.token.VariableToken;

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

    public void add(String regex, TokenType tokenType) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), tokenType));
    }

    public LinkedList<Token> tokenize(String postfixExpression) {
        LinkedList<Token> tokens = new LinkedList<>();
        String s = postfixExpression.trim();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.getRegex().matcher(s);
                if (m.find()) {
                    match = true;
                    String token = m.group().trim();
                    s = m.replaceFirst("").trim();
                    if (info.getTokenType() == TokenType.OPERATOR) {
                        tokens.add(new OperatorToken(token));
                    } else if (info.getTokenType() == TokenType.CONSTANT) {
                        tokens.add(new ConstantToken(token));
                    } else if (info.getTokenType() == TokenType.FUNCTION) {
                        tokens.add(new OperatorToken(token));
                    } else if (info.getTokenType() == TokenType.VARIABLE) {
                        tokens.add(new VariableToken(token));
                    }
                    break;
                }
            }
            if (!match) {
                throw new InvalidExpressionException("Unexpected character in expression - " + s);
            }
        }
        return tokens;
    }
}

package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.operator.Operators;
import com.evaluator.parser.token.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This is shunting yard algorithm for converting infix expression to postfix expression.
 *
 * @author shrikrushna on 2020-04-20
 */
public class InfixToPostfixTokenizer {

    private static final String PAREN_LEFT = "[(]";
    private static final String PAREN_RIGHT = "[)]";
    private static final String FUNCTION_ARGSEP = "[;]";
    private static final String VAR_NAME = "(\\$.*?\\$)";
    private static final String NUMBER = "0?-?\\+?\\d+(\\.\\d+)?";
    private static final String DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private static final String SPACE = "[\\s]";

    /**
     * Parses input string expression and returns tokens to be used for building expression.
     *
     * @param expr input infix expression
     * @return Tokens in postfix form.
     */
    public static List<Token> tokenize(String expr) {
        Stack<String> operators = new Stack<>();
        List<Token> tokensToReturn = new LinkedList<>();
        String[] tokens = expr.split(SPACE);
        int tokensRemaining = tokens.length;

        for (String token : tokens) {
            if (isNumber(token)) {
                tokensToReturn.add(new ConstantToken(token));
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.matches(FUNCTION_ARGSEP)) {
                while (!operators.empty() && operators.peek().matches(PAREN_RIGHT)) {
                    if (operators.empty() && !operators.peek().matches(PAREN_RIGHT)) {
                        throw new InvalidExpressionException("Mismatched Parentheses.");
                    }
                }
            } else if (isOperator(token)) {
                while (!operators.empty() && ((isOperator(operators.peek())
                        && ((isLeftAssociative(token) && ((operatorPrecedence(token) <= operatorPrecedence(operators.peek()))
                        || ((!isLeftAssociative(token) && ((operatorPrecedence(token) < operatorPrecedence(operators.peek()))))))))))) {
                    tokensToReturn.add(new OperatorToken(operators.pop()));
                }
                operators.push(token);
            } else if (/*!operators.empty() &&*/ token.matches(PAREN_LEFT)) {
                operators.push(token);
            } else if (!operators.empty() && token.matches(PAREN_RIGHT)) {
                while (!operators.empty() && !operators.peek().matches(PAREN_LEFT)) {
                    tokensToReturn.add(new OperatorToken(operators.pop()));
                }
                if (!operators.empty()) {
                    operators.pop();
                } else if (!operators.empty() && isFunction(operators.peek())) {
                    String poppedToken = operators.pop();
                    tokensToReturn.add(new OperatorToken(poppedToken));
                } else if (operators.empty()) {
                    throw new InvalidExpressionException("Mismatched Parentheses.");
                }
            } else if (token.matches(DATE)) {
                tokensToReturn.add(new DateToken(token));
            } else if (token.matches(VAR_NAME)) {
                tokensToReturn.add(new VariableToken(token));
            } else {
                tokensToReturn.add(new ConstantToken(token));
            }
            tokensRemaining--;
        }

        if (tokensRemaining == 0) {
            while (!operators.empty()) {
                if (operators.peek().matches(PAREN_LEFT) || operators.peek().matches(PAREN_RIGHT)) {
                    throw new InvalidExpressionException("Mismatched Parentheses.");
                }
                tokensToReturn.add(new OperatorToken(operators.pop()));
            }
        }

        return tokensToReturn;
    }

    private static boolean isNumber(String str) {
        return str.matches(NUMBER);
    }

    private static boolean isOperator(String symbol) {
        return Operators.isOperator(symbol);
    }

    private static boolean isLeftAssociative(String symbol) {
        return Operators.isLeftAssociative(symbol);
    }

    private static int operatorPrecedence(String symbol) {
        return Operators.getPrecedence(symbol);
    }

    private static boolean isFunction(String str) {
        return "pow".equals(str);
    }
}

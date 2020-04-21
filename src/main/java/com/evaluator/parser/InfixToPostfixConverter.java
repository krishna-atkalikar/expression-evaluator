package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.operator.OperationType;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author shrikrushna on 2020-04-20
 */
public class InfixToPostfixConverter {

    private static final String PAREN_LEFT = "[(]";
    private static final String PAREN_RIGHT = "[)]";
    private static final String FUNCTION_ARGSEP = "[;]";
    private static final String VAR_NAME = "\"[^\"]*\"|((?=_[a-z_A-Z_0-9]|[A-Z_a-z])[A-Z_a-z_0-9]+)";
    private static final String NUMBER = "0?-?\\+?\\d+(\\.\\d+)?";

    public static String convert(String expr) {
        Stack<String> operators = new Stack<>();
        Queue<String> output = new LinkedList<>();
        String[] tokens = expr.split("[\\s]");
        StringBuilder postfixStr = new StringBuilder();
        int tokensRemaining = tokens.length;

        for (String token : tokens) {
            if (isNumber(token)) {
                output.offer(token);
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.matches(FUNCTION_ARGSEP)) {
                while (!operators.empty() && operators.peek().matches(PAREN_RIGHT)) {
                    output.offer(operators.pop());
                    if (operators.empty() && !operators.peek().matches(PAREN_RIGHT)) {
                        throw new InvalidExpressionException("Mismatched Parentheses.");
                    }
                }
            } else if (isOperator(token)) {
                while (!operators.empty() && ((isOperator(operators.peek())
                        && ((isLeftAssociative(token) && ((operatorPrecedence(token) <= operatorPrecedence(operators.peek()))
                        || ((!isLeftAssociative(token) && ((operatorPrecedence(token) < operatorPrecedence(operators.peek()))))))))))) {
                    output.offer(operators.pop());
                }
                operators.push(token);
            } else if (/*!operators.empty() &&*/ token.matches(PAREN_LEFT)) {
                operators.push(token);
            } else if (!operators.empty() && token.matches(PAREN_RIGHT)) {
                while (!operators.empty() && !operators.peek().matches(PAREN_LEFT)) {
                    output.offer(operators.pop());
                }
                if (!operators.empty()) {
                    operators.pop();
                } else if (!operators.empty() && isFunction(operators.peek())) {
                    output.offer(operators.pop());
                } else if (operators.empty()) {
                    throw new InvalidExpressionException("Mismatched Parentheses.");
                }
            } else if (token.matches(VAR_NAME)) {
                output.offer(token);
            }
            tokensRemaining--;
        }

        if (tokensRemaining == 0) {
            while (!operators.empty()) {
                if (operators.peek().matches(PAREN_LEFT) || operators.peek().matches(PAREN_RIGHT)) {
                    throw new InvalidExpressionException("Mismatched Parentheses.");
                }
                output.offer(operators.pop());
            }
        }

        while (!output.isEmpty()) {
            while (output.size() > 1) {
                postfixStr.append(output.poll()).append(" ");
            }
            postfixStr.append(output.poll());
        }
        return postfixStr.toString();
    }

    private static boolean isNumber(String str) {
        return str.matches(NUMBER);
    }

    private static boolean isOperator(String str) {
        return OperationType.isOperator(str);
    }

    private static boolean isLeftAssociative(String str) {
        return OperationType.from(str).isLeftAssociative();
    }

    private static int operatorPrecedence(String str) {
        return OperationType.from(str).getPrecedence();
    }

    private static boolean isFunction(String str) {
        return "pow".equals(str);
    }

}

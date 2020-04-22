package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.expression.Expression;
import com.evaluator.operator.Operators;
import com.evaluator.parser.token.DateToken;
import com.evaluator.parser.token.Token;

import java.util.List;
import java.util.Stack;

import static com.evaluator.factory.Expressions.*;

/**
 * Expression builder which takes tokens as input and output a well formed expression that can be evaluated.
 *
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionBuilder {

    /**
     * Builds expression from given tokens. To get well a formed expression tokens needs to be postfix order.
     *
     * @param tokens list of tokens
     * @return a well formed expression that is ready for evaluation.
     */
    public static Expression build(List<Token> tokens) {
        Stack<Expression> expressions = new Stack<>();
        for (Token token : tokens) {
            if (token.isVariableToken()) {
                expressions.push(param(token.getToken()));
            } else if (token.isDateToken()) {
                DateToken dateToken = token.toDateToken();
                expressions.push(constant(dateToken.getDate()));
            } else if (token.isConstantToken()) {
                expressions.push(constant(token.getToken()));
            } else if (token.isOperatorToken()) {
                if (Operators.isUnary(token.getToken())) {
                    expressions.push(Operators.unaryExprBuilderFunction(token.getToken()).apply(expressions.pop()));
                } else {
                    Expression right = expressions.pop();
                    Expression left = expressions.pop();
                    if (Operators.isBinary(token.getToken())) {
                        expressions.push(Operators.binaryExprBuilderFunction(token.getToken()).apply(left, right));
                    } else if (Operators.isTernary(token.getToken())) {
                        expressions.push(iff(expressions.pop(), left, right));
                    }
                }
            }
        }
        if (expressions.size() != 1) {
            throw new InvalidExpressionException("Invalid expression." + expressions);
        }
        return expressions.pop();
    }
}
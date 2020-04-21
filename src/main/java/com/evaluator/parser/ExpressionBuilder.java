package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.expression.Expression;
import com.evaluator.factory.Expressions;
import com.evaluator.operator.BinaryOperator;
import com.evaluator.operator.UnaryOperator;
import com.evaluator.parser.token.DateToken;
import com.evaluator.parser.token.Token;

import java.util.List;
import java.util.Stack;

import static com.evaluator.factory.Expressions.constant;
import static com.evaluator.factory.Expressions.param;
import static com.evaluator.operator.OperationType.IF;

/**
 * Expression builder which takes tokens as input and output a well formed expression that can be evaluated.
 *
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionBuilder {

    public static Expression build(List<Token> list) {
        Stack<Expression> expressions = new Stack<>();

        for (Token token : list) {
            if (token.isVariableToken()) {
                expressions.push(param(token.getToken()));
            } else if (token.isDateToken()) {
                DateToken dateToken = token.toDateToken();
                expressions.push(constant(dateToken.getDate()));
            } else if (token.isConstantToken()) {
                expressions.push(constant(token.getToken()));
            } else if (token.isOperatorToken()) {
                if (UnaryOperator.contains(token.getToken())) {
                    expressions.push(UnaryOperator.from(token.getToken()).getExprBuilderFunction().apply(expressions.pop()));
                } else {
                    Expression right = expressions.pop();
                    Expression left = expressions.pop();
                    if (BinaryOperator.contains(token.getToken())) {
                        expressions.push(BinaryOperator.forSymbol(token.getToken()).getExprBuilderFunction().apply(left, right));
                    } else if (token.getToken().equalsIgnoreCase(IF.getSymbol())) {
                        expressions.push(Expressions.iff(expressions.pop(), left, right));
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
package com.evaluator.parser.token;

import com.evaluator.expression.Expression;
import com.evaluator.operator.Operators;

import static com.evaluator.factory.Expressions.iff;

/**
 * Represents an Operator token
 *
 * @author shrikrushna on 2020-04-19
 */
public class OperatorToken extends Token {

    public OperatorToken(String token) {
        super(token);
    }

    @Override
    public boolean isOperatorToken() {
        return true;
    }

    @Override public Expression toExpression(Expression... expressions) {
        if (Operators.isUnary(getToken())) {
            return Operators.unaryExprBuilderFunction(getToken()).apply(expressions[0]);
        }
        if (Operators.isBinary(getToken())) {
            return Operators.binaryExprBuilderFunction(getToken()).apply(expressions[0], expressions[1]);
        }
        return iff(expressions[0], expressions[1], expressions[2]);
    }
}

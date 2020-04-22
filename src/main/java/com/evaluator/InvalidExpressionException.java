package com.evaluator;

import com.evaluator.expression.Expression;

/**
 * @author shrikrushna on 2020-04-20
 */
public class InvalidExpressionException extends RuntimeException {

    public InvalidExpressionException() {
    }

    public InvalidExpressionException(Expression expression) {
        this("Invalid expression " + expression);
    }

    public InvalidExpressionException(String message) {
        super(message);
    }
}

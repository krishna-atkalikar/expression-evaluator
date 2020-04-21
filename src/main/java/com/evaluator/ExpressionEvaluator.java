package com.evaluator;

import com.evaluator.expression.Expression;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import com.google.common.collect.ImmutableMap;

/**
 * Evaluates expression and returns result.
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionEvaluator<T> {

    /**
     * Accepts an expression and data set to be evaluated. and returns the result of expression.
     *
     * @param expression expression to be evaluated.
     * @param dataset    parameter name to it value map.
     * @return result of expression evaluation.
     */
    public T evaluate(Expression expression, ImmutableMap<String, Object> dataset) {
        Visitor<T> visitor = new ExpressionVisitor<>(dataset);
        return visitor.visit(expression);
    }

}

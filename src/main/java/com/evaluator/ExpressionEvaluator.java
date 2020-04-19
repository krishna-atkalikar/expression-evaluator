package com.evaluator;

import com.evaluator.expression.Expression;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import com.google.common.collect.ImmutableMap;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionEvaluator<T> {

    public T evaluate(Expression expression, ImmutableMap<String, Object> params) {
        Visitor<T> visitor = new ExpressionVisitor<>(params);
        return visitor.visit(expression);
    }

}

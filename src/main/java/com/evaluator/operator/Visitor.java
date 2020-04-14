package com.evaluator.operator;

import com.evaluator.expression.Expression;

/**
 * @author shrikrushna on 2020-04-12
 */
public interface Visitor<T, R> {

    R visit(Expression<T, R> expression);
}

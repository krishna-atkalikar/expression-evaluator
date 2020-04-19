package com.evaluator.visitor;

import com.evaluator.expression.*;

/**
 * @author shrikrushna on 2020-04-16
 */
public interface Visitor<T> {

    T visit(Expression expression);

    T visit(UnaryExpression unaryExpression);

    T visit(BinaryExpression binaryExpression);

    T visit(TernaryExpression ternaryExpression);

    T visit(ConstantExpression constantExpression);
}

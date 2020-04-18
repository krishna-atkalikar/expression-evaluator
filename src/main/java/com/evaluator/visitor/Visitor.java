package com.evaluator.visitor;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.ConstantExpression;
import com.evaluator.expression.Expression;
import com.evaluator.expression.TernaryExpression;

/**
 * @author shrikrushna on 2020-04-16
 */
public interface Visitor<T> {

    T visit(Expression expression);

    T visit(BinaryExpression binaryExpression);

    T visit(ConstantExpression constantExpression);

    T visit(TernaryExpression ternaryExpression);
}

package com.evaluator.visitor;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.ConstantExpression;
import com.evaluator.expression.Expression;
import com.evaluator.expression.TernaryExpression;
import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-16
 */
public class ExpressionVisitor<T> implements Visitor<T> {

    @Override
    public T visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        Operator op = binaryExpression.getOperator();
        T left = visit(binaryExpression.getLeft());
        T right = visit(binaryExpression.getRight());
        return op.apply(left, right);
    }

    @Override
    public T visit(ConstantExpression expression) {
        return (T) expression.getValue();
    }

    @Override public T visit(TernaryExpression ternaryExpression) {
        T condition = visit(ternaryExpression.getCondition());
        if ((Boolean) condition) {
            return visit(ternaryExpression.getIfTrue());
        } else {
            return visit(ternaryExpression.getOrElse());
        }
    }
}

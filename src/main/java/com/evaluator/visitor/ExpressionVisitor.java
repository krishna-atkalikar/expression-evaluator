package com.evaluator.visitor;

import com.evaluator.expression.*;
import com.evaluator.operator.Operators;

/**
 * @author shrikrushna on 2020-04-16
 */
public class ExpressionVisitor<T> implements Visitor<T> {

    @Override
    public T visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        T visit = visit(unaryExpression.getExpression());
        return (T) Operators.getUnary(unaryExpression.getOperationType()).apply(visit);
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        Object left = visit(binaryExpression.getLeft());
        Object right = visit(binaryExpression.getRight());
        return (T) Operators.getBinary(binaryExpression.getOperationType()).apply(left, right);
    }

    @Override
    public T visit(TernaryExpression ternaryExpression) {
        T condition = visit(ternaryExpression.getCondition());
        if ((Boolean) condition) {
            return visit(ternaryExpression.getIfTrue());
        } else {
            return visit(ternaryExpression.getOrElse());
        }
    }

    @Override
    public T visit(ConstantExpression expression) {
        return (T) expression.getValue();
    }
}

package com.evaluator.expression;

import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-12
 */
public class LogicalExpression<T> extends Expression<T, Boolean> {

    protected Operator<T, Boolean> op;
    private Expression<T, T> left;
    private Expression<T, T> right;

    public LogicalExpression(Operator<T, Boolean> op, Expression<T, T> left, Expression<T, T> right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Expression<T, T> getLeft() {
        return left;
    }

    public Expression<T, T> getRight() {
        return right;
    }

    @Override
    public Boolean evaluate() {
        return op.visit(this);
    }
}

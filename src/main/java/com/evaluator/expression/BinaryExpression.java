package com.evaluator.expression;

import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpression<T, R> extends Expression<T, R> {

    private Operator<T, R> op;
    private Expression<T, R> left;
    private Expression<T, R> right;

    public BinaryExpression(Operator<T, R> op, Expression<T, R> left, Expression<T, R> right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Expression<T, R> getLeft() {
        return left;
    }

    public Expression<T, R> getRight() {
        return right;
    }

    public R evaluate() {
        return op.visit(this);
    }
}

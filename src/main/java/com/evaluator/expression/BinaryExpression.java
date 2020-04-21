package com.evaluator.expression;

import com.evaluator.operator.OperationType;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpression implements Expression {

    private Expression left;
    private Expression right;
    private OperationType operationType;

    public BinaryExpression(Expression left, Expression right, OperationType operationType) {
        this.left = left;
        this.right = right;
        this.operationType = operationType;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

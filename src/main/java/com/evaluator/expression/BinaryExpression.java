package com.evaluator.expression;

import com.evaluator.operator.BinaryOperator;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpression implements Expression {

    private Expression left;
    private Expression right;
    private BinaryOperator binaryOperator;

    public BinaryExpression(Expression left, Expression right, BinaryOperator binaryOperator) {
        this.left = left;
        this.right = right;
        this.binaryOperator = binaryOperator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public BinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "BinaryExpression{" +
                "left=" + left +
                ", right=" + right +
                ", binaryOperator=" + binaryOperator +
                '}';
    }
}

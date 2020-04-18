package com.evaluator.expression;

import com.evaluator.operator.Operator;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpression extends Expression {

    private Operator operator;
    private Expression left;
    private Expression right;

    public BinaryExpression(Operator operator, Expression left, Expression right) {

        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

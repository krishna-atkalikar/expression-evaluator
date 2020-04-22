package com.evaluator.expression;

import com.evaluator.operator.UnaryOperator;
import com.evaluator.visitor.Visitor;

/**
 * Represents a unary expression.
 *
 * @author shrikrushna on 2020-04-19
 */
public class UnaryExpression implements Expression {

    private Expression expression;
    private UnaryOperator unaryOperator;

    public UnaryExpression(Expression expression, UnaryOperator unaryOperator) {
        this.expression = expression;
        this.unaryOperator = unaryOperator;
    }

    public Expression getExpression() {
        return expression;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "UnaryExpression{" +
                "expression=" + expression +
                ", unaryOperator=" + unaryOperator +
                '}';
    }
}

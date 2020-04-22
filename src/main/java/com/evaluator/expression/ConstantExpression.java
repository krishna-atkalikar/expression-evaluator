package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * Represents a constant value expression. For eg. for constant integer 10, 2, 5 etc or any other value.
 *
 * @author shrikrushna on 2020-04-16
 */
public class ConstantExpression implements Expression {

    private Object value;

    public ConstantExpression(Object value) {
        this.value = value;
    }

    public Object getValue() {
        try {
            return Double.valueOf(value.toString());
        } catch (NumberFormatException nfe) {
            return value;
        }
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ConstantExpression{" +
                "value=" + value +
                '}';
    }
}

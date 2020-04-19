package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-16
 */
public class ConstantExpression extends Expression {


    private Object value;

    public ConstantExpression(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

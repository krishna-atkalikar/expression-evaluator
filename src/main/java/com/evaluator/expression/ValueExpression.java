package com.evaluator.expression;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ValueExpression<T, R> extends Expression<T, R> {

    private R value;

    public ValueExpression(R value) {
        this.value = value;
    }

    @Override public R evaluate() {
        return value;
    }
}

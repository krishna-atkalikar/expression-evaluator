package com.evaluator.expression;

/**
 * @author shrikrushna on 2020-04-11
 */
public class TernaryExpression<T, R> extends Expression<T, R> {


    private LogicalExpression<T> condition;
    private Expression<T, R> then;
    private Expression<T, R> orElse;

    public TernaryExpression(LogicalExpression<T> condition, Expression<T, R> then, Expression<T, R> orElse) {
        this.condition = condition;
        this.then = then;
        this.orElse = orElse;
    }

    @Override
    public R evaluate() {
        if (condition.evaluate()) {
            return then.evaluate();
        } else {
            return orElse.evaluate();
        }
    }
}

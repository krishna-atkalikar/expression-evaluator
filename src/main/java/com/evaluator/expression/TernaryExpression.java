package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-18
 */
public class TernaryExpression extends Expression {

    private Expression condition;
    private Expression ifTrue;
    private Expression orElse;

    public TernaryExpression(Expression condition, Expression ifTrue, Expression orElse) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.orElse = orElse;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getIfTrue() {
        return ifTrue;
    }

    public Expression getOrElse() {
        return orElse;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

package com.evaluator.expression;

import com.evaluator.operator.TernaryOperator;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-18
 */
public class TernaryExpression implements Expression {

    private final TernaryOperator ternaryOperator;
    private Expression condition;
    private Expression ifTrue;
    private Expression orElse;

    public TernaryExpression(Expression condition, Expression ifTrue, Expression orElse, TernaryOperator ternaryOperator) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.orElse = orElse;
        this.ternaryOperator = ternaryOperator;
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

    public TernaryOperator getTernaryOperator() {
        return ternaryOperator;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

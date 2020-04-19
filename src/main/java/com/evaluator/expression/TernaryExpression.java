package com.evaluator.expression;

import com.evaluator.operator.OperationType;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-18
 */
public class TernaryExpression extends Expression {

    private final OperationType type;
    private Expression condition;
    private Expression ifTrue;
    private Expression orElse;

    public TernaryExpression(Expression condition, Expression ifTrue, Expression orElse, OperationType type) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.orElse = orElse;
        this.type = type;
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

    public OperationType getType() {
        return type;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

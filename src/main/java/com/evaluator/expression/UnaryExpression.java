package com.evaluator.expression;

import com.evaluator.operator.OperationType;
import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-19
 */
public class UnaryExpression implements Expression {

    private Expression expression;
    private OperationType operationType;

    public UnaryExpression(Expression expression, OperationType operationType) {
        this.expression = expression;
        this.operationType = operationType;
    }

    public Expression getExpression() {
        return expression;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}

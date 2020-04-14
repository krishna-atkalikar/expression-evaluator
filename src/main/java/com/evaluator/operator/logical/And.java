package com.evaluator.operator.logical;

import com.evaluator.expression.Expression;
import com.evaluator.expression.LogicalExpression;
import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-12
 */
public class And extends Operator<Boolean, Boolean> {

    @Override
    public Boolean visit(Expression<Boolean, Boolean> expression) {
        LogicalExpression<Boolean> expr = (LogicalExpression<Boolean>) expression;
        Boolean left = expr.getLeft().evaluate();
        Boolean right = expr.getRight().evaluate();
        return left && right;
    }
}
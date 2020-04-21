package com.evaluator.visitor;

import com.evaluator.InvalidDataSetException;
import com.evaluator.expression.*;
import com.evaluator.operator.Operators;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shrikrushna on 2020-04-16
 */
public class ExpressionVisitor<T> implements Visitor<T> {

    private Map<String, Object> context = new HashMap<>();

    public ExpressionVisitor(Map<String, Object> context) {
        this.context = context;
    }

    public ExpressionVisitor() {
    }

    @Override
    public T visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public T visit(UnaryExpression unaryExpression) {
        T visit = visit(unaryExpression.getExpression());
        return (T) Operators.get(unaryExpression.getUnaryOperator()).apply(visit);
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
        Object left = visit(binaryExpression.getLeft());
        Object right = visit(binaryExpression.getRight());
        return (T) Operators.get(binaryExpression.getBinaryOperator()).apply(left, right);
    }

    @Override
    public T visit(TernaryExpression ternaryExpression) {
        T condition = visit(ternaryExpression.getCondition());
        if ((Boolean) condition) {
            return visit(ternaryExpression.getIfTrue());
        } else {
            return visit(ternaryExpression.getOrElse());
        }
    }

    @Override
    public T visit(ConstantExpression expression) {
        return (T) expression.getValue();
    }

    @Override public T visit(ParameterExpression parameterExpression) {
        String paramName = parameterExpression.getParamName();
        if (!context.containsKey(paramName)) {
            throw new InvalidDataSetException(parameterExpression);
        }
        Object o = context.get(paramName);
        if (o instanceof Expression)
            return visit((Expression) o);
        else
            return (T) o;
    }
}

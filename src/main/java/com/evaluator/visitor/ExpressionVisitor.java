package com.evaluator.visitor;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.ConstantExpression;
import com.evaluator.expression.Expression;
import com.evaluator.expression.TernaryExpression;
import com.evaluator.operator.Operators;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author shrikrushna on 2020-04-16
 */
public class ExpressionVisitor<T> implements Visitor<T> {

    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
    };
    private final Type type = typeToken.getType();

    @Override
    public T visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public T visit(BinaryExpression binaryExpression) {
//        Operator op = binaryExpression.getOperator();
        Object left = visit(binaryExpression.getLeft());
        Object right = visit(binaryExpression.getRight());
        return (T) Operators.get(binaryExpression.getOperationType()).apply(left, right);
    }

    @Override
    public T visit(ConstantExpression expression) {
        return (T) expression.getValue();
    }

    @Override public T visit(TernaryExpression ternaryExpression) {
        T condition = visit(ternaryExpression.getCondition());
        if ((Boolean) condition) {
            return visit(ternaryExpression.getIfTrue());
        } else {
            return visit(ternaryExpression.getOrElse());
        }
    }
}

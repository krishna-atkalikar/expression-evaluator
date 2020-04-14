package com.evaluator.operator.arithmetic;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.Expression;
import com.evaluator.operator.Operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shrikrushna on 2020-04-12
 */
public class Min<T, R> extends Operator<T, R> {

    @Override
    public R visit(Expression<T, R> expression) {
        BinaryExpression<T, R> expr = (BinaryExpression<T, R>) expression;
        R left = expr.getLeft().evaluate();
        R right = expr.getRight().evaluate();
        try {
            Method method = this.getClass()
                    .getMethod("visit" + left.getClass().getSimpleName(), left.getClass(), right.getClass());
            method.setAccessible(true);
            return (R) method.invoke(this, left, right);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double visitDouble(Double left, Double right) {
        return Math.min(left, right);
    }

    public Integer visitInteger(Integer left, Integer right) {
        return Math.min(left, right);
    }

    public Float visitFloat(Float left, Float right) {
        return Math.min(left, right);
    }
}
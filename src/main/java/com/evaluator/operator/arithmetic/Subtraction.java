package com.evaluator.operator.arithmetic;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.Expression;
import com.evaluator.operator.Operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shrikrushna on 2020-04-12
 */
public class Subtraction<T, R> extends Operator<T, R> {

    @Override
    public R visit(Expression<T, R> expression) {
        BinaryExpression<T, R> expr = (BinaryExpression<T, R>) expression;
        R evaluate = expr.getLeft().evaluate();
        R evaluate1 = expr.getRight().evaluate();
        try {
            Method method = this.getClass().getMethod("visit" + evaluate.getClass().getSimpleName(), evaluate.getClass(), evaluate1.getClass());
            method.setAccessible(true);
            return (R) method.invoke(this, evaluate, evaluate1);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Double visitDouble(Double left, Double right) {
        return left - right;
    }

    public Integer visitInteger(Integer left, Integer right) {
        return left - right;
    }

    public Float visitFloat(Float left, Float right) {
        return left - right;
    }
}
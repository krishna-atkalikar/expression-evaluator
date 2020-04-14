package com.evaluator.operator.logical;

import com.evaluator.expression.Expression;
import com.evaluator.expression.LogicalExpression;
import com.evaluator.operator.Operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shrikrushna on 2020-04-12
 */
public class LessThan<T> extends Operator<T, Boolean> {

    @Override
    public Boolean visit(Expression<T, Boolean> expression) {
        LogicalExpression<T> expr = (LogicalExpression<T>) expression;
        T left = expr.getLeft().evaluate();
        T right = expr.getRight().evaluate();
        try {
            Method method = this.getClass().getMethod("visit" + left.getClass().getSimpleName(), left.getClass(), right.getClass());
            method.setAccessible(true);
            return (Boolean) method.invoke(this, left, right);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Boolean visitDouble(Double left, Double right) {
        return left < right;
    }

    public Boolean visitInteger(Integer left, Integer right) {
        return left < right;
    }
//
//    public Float visitFloat(Float left, Float right) {
//        return left + right;
//    }
}
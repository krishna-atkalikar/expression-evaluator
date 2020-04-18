package com.evaluator.operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shrikrushna on 2020-04-18
 */
public abstract class Operator {

    public <T> T apply(T left, T right) {
        try {
            String methodName = "do" + this.getClass().getSimpleName();
            System.out.println("methodName = " + methodName);
            Method method = this.getClass().getMethod(methodName, left.getClass(), right.getClass());
            method.setAccessible(true);
            return (T) method.invoke(this, left, right);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed");
        }
    }
}

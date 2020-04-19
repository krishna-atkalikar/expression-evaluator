package com.evaluator.operator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shrikrushna on 2020-04-18
 */
public abstract class Operator {

    public Object apply(Object left, Object right) {
        try {
            String methodName = "perform";
            System.out.println("methodName = " + methodName);
            Method method = this.getClass().getMethod(methodName, left.getClass(), right.getClass());
            method.setAccessible(true);
            return method.invoke(this, left, right);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed");
        }
    }
}

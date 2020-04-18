//package com.evaluator;
//
//import com.evaluator.expression.BinaryExpression;
//import com.evaluator.expression.Expression;
//import com.evaluator.expression.ValueExpression;
//import com.evaluator.operator.arithmetic.Addition;
//
///**
// * @author shrikrushna on 2020-04-16
// */
//public class Expressions {
//
//
//    public static  <T, R> Expression<T, R> add(Expression<T, R> left, Expression<T, R> right) {
//        return new BinaryExpression<>(new Addition<>(), left, right);
//    }
//
//    public static <T, R> Expression<T, R> value(R value) {
//        return new ValueExpression<>(value);
//    }
//
//}

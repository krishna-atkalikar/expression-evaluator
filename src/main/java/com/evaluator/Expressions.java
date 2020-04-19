package com.evaluator;

import com.evaluator.expression.*;
import com.evaluator.operator.OperationType;

import java.time.LocalDate;

/**
 * @author shrikrushna on 2020-04-16
 */
public class Expressions {

    public static Expression add(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.ADD);
    }

    public static Expression sub(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.SUBTRACT);
    }

    public static Expression multiply(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.MULTIPLICATION);
    }

    public static Expression divide(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.DIVISION);
    }

    public static Expression dateDifference(LocalDate left, LocalDate right) {
        return new BinaryExpression(constant(left), constant(right), OperationType.DATE_DIFFERENCE);
    }

    public static Expression addDays(LocalDate date, int daysToBeAdded) {
        return new BinaryExpression(constant(date), constant(daysToBeAdded), OperationType.ADD_DAYS_TO_DATE);
    }

    public static Expression subtractDays(LocalDate date, int daysToBeSubtracted) {
        return new BinaryExpression(constant(date), constant(daysToBeSubtracted), OperationType.SUBTRACT_DAYS_FROM_DATE);
    }

    public static Expression lt(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.LT);
    }

    public static Expression lte(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.LTE);
    }

    public static Expression gt(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.GT);
    }

    public static Expression gte(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.GTE);
    }

    public static Expression eq(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.EQ);
    }

    public static Expression nEq(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.NEQ);
    }

    public static Expression and(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.AND);
    }

    public static Expression or(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.OR);
    }

    public static Expression pow(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.POWER);
    }

    public static Expression min(Expression left, Expression right) {
        return new BinaryExpression(left, right, OperationType.MIN);
    }

    public static Expression not(Expression left) {
        return new UnaryExpression(left, OperationType.NOT);
    }

    public static Expression floor(Expression left) {
        return new UnaryExpression(left, OperationType.FLOOR);
    }

    public static Expression ceil(Expression left) {
        return new UnaryExpression(left, OperationType.CEILING);
    }

    public static Expression iff(Expression condition, Expression ifTrue, Expression orElse) {
        return new TernaryExpression(condition, ifTrue, orElse, OperationType.IF);
    }

    public static Expression constant(Object value) {
        return new ConstantExpression(value);
    }

}

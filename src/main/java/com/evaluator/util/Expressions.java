package com.evaluator.util;

import com.evaluator.expression.*;
import com.evaluator.operator.BinaryOperator;
import com.evaluator.operator.TernaryOperator;
import com.evaluator.operator.UnaryOperator;

/**
 * A utility class to create different expressions.
 *
 * @author shrikrushna on 2020-04-16
 */
public class Expressions {

	public static Expression createBinaryExpression(Expression left, Expression right, BinaryOperator binaryOperator) {
		return new BinaryExpression(left, right, binaryOperator);
	}

	public static Expression add(Expression left, Expression right) {
		return new BinaryExpression(left, right, BinaryOperator.ADD);
	}

	public static Expression sub(Expression left, Expression right) {
		return new BinaryExpression(left, right, BinaryOperator.SUBTRACT);
	}

	public static Expression multiply(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.MULTIPLICATION);
    }

    public static Expression divide(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.DIVISION);
    }

    public static Expression dateDifference(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.DATE_DIFFERENCE);
    }

    public static Expression addDays(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.ADD_DAYS_TO_DATE);
    }

    public static Expression subtractDays(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.SUBTRACT_DAYS_FROM_DATE);
    }

    public static Expression lt(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.LT);
    }

    public static Expression lte(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.LTE);
    }

    public static Expression gt(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.GT);
    }

    public static Expression gte(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.GTE);
    }

    public static Expression eq(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.EQ);
    }

    public static Expression nEq(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.NEQ);
    }

    public static Expression and(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.AND);
    }

    public static Expression or(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.OR);
    }

    public static Expression pow(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.POWER);
    }

    public static Expression min(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperator.MIN);
    }

    public static Expression not(Expression left) {
        return new UnaryExpression(left, UnaryOperator.NOT);
    }

    public static Expression floor(Expression left) {
        return new UnaryExpression(left, UnaryOperator.FLOOR);
    }

    public static Expression ceil(Expression left) {
        return new UnaryExpression(left, UnaryOperator.CEILING);
    }

    public static Expression iff(Expression condition, Expression ifTrue, Expression orElse) {
        return new TernaryExpression(condition, ifTrue, orElse, TernaryOperator.IF);
    }

    public static Expression constant(Object value) {
        return new ConstantExpression(value);
    }

    public static Expression param(String value) {
        return new ParameterExpression(value);
    }
}

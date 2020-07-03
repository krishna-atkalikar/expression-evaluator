package com.evaluator.operator;

import com.evaluator.expression.Expression;
import com.evaluator.util.Expressions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;

/**
 * An enum where all the binary operators are present along with their attributes and necessary details.
 *
 * @author shrikrushna on 2020-04-21
 */
public enum BinaryOperator implements Operator {

	ADD("+", 2, BinaryOperator::performAddition),
	SUBTRACT("-", 2, (l, r) -> Double.parseDouble(l.toString()) - Double.parseDouble(r.toString())),
	MULTIPLICATION("*", 3, (l, r) -> Double.parseDouble(l.toString()) * Double.parseDouble(r.toString())),
	DIVISION("/", 3, (l, r) -> Double.parseDouble(l.toString()) / Double.parseDouble(r.toString())),

	LT("<", 1, (l, r) -> Double.parseDouble(l.toString()) < Double.parseDouble(r.toString())),
	LTE("<=", 1, (l, r) -> Double.parseDouble(l.toString()) <= Double.parseDouble(r.toString())),
	GT(">", 1, (l, r) -> Double.parseDouble(l.toString()) > Double.parseDouble(r.toString())),
	GTE(">=", 1, (l, r) -> Double.parseDouble(l.toString()) >= Double.parseDouble(r.toString())),
	EQ("==", 1, (l, r) -> Double.valueOf(l.toString()).equals(Double.valueOf(r.toString()))),
	NEQ("!=", 1, (l, r) -> !Double.valueOf(l.toString()).equals(Double.valueOf(r.toString()))),

	AND("&&", 1, (l, r) -> Boolean.parseBoolean(l.toString()) && Boolean.parseBoolean(r.toString())),
	OR("||", 1, (l, r) -> Boolean.parseBoolean(l.toString()) || Boolean.parseBoolean(r.toString())),


	POWER("POW", 4, (l, r) -> Math.pow(Double.parseDouble(l.toString()), Double.parseDouble(r.toString()))),
	MIN("MIN", 4, (l, r) -> Math.min(Double.parseDouble(l.toString()), Double.parseDouble(r.toString()))),


	DATE_DIFFERENCE("DATE_DIFF", 4, (l, r) -> ChronoUnit.DAYS.between((LocalDate) r, (LocalDate) l)),
	ADD_DAYS_TO_DATE("ADD_DAYS_TO_DATE", 4, (l, r) -> ((LocalDate) l).plusDays(((Double) r).intValue())),
	SUBTRACT_DAYS_FROM_DATE("SUBTRACT_DAYS_FROM_DATE", 4, (l, r) -> ((LocalDate) l).minusDays(((Double) r).intValue()));

	private final String symbol;
	private final int precedence;
	private final boolean isLeftAssociative;
	private final BiFunction<Expression, Expression, Expression> exprBuilderFunction;
	private final BiFunction<Object, Object, Object> evaluationFunction;

	BinaryOperator(String symbol, int precedence, BiFunction<Object, Object, Object> evaluationFunction) {
		this.symbol = symbol;
		this.precedence = precedence;
		this.isLeftAssociative = true;
		this.exprBuilderFunction = (l, r) -> Expressions.createBinaryExpression(l, r, this);
		this.evaluationFunction = evaluationFunction;
	}

	private static Object performAddition(Object l, Object r) {
		try {
			return Double.parseDouble(l.toString()) + Double.parseDouble(r.toString());
		} catch (Exception ex) {
			return l.toString() + r.toString();
		}
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public int getPrecedence() {
		return precedence;
	}

	@Override
	public boolean isLeftAssociative() {
		return isLeftAssociative;
	}

	public BiFunction<Expression, Expression, Expression> getExprBuilderFunction() {
		return exprBuilderFunction;
	}

	public BiFunction<Object, Object, Object> getEvaluationFunction() {
		return evaluationFunction;
	}
}

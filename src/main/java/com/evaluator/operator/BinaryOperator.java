package com.evaluator.operator;

import com.evaluator.expression.Expression;
import com.evaluator.factory.Expressions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;

/**
 * An enum where all the binary operators are present along with their attributes and necessary details.
 *
 * @author shrikrushna on 2020-04-21
 */
public enum BinaryOperator implements Operator {

    ADD("+", 2, true, Expressions::add, BinaryOperator::performAddition),
    SUBTRACT("-", 2, true, Expressions::sub, (l, r) -> Double.valueOf(l.toString()) - Double.valueOf(r.toString())),
    MULTIPLICATION("*", 3, true, Expressions::multiply, (l, r) -> Double.valueOf(l.toString()) * Double.valueOf(r.toString())),
    DIVISION("/", 3, true, Expressions::divide, (l, r) -> Double.valueOf(l.toString()) / Double.valueOf(r.toString())),

    LT("<", 1, true, Expressions::lt, (l, r) -> Double.valueOf(l.toString()) < Double.valueOf(r.toString())),
    LTE("<=", 1, true, Expressions::lte, (l, r) -> Double.valueOf(l.toString()) <= Double.valueOf(r.toString())),
    GT(">", 1, true, Expressions::gt, (l, r) -> Double.valueOf(l.toString()) > Double.valueOf(r.toString())),
    GTE(">=", 1, true, Expressions::gte, (l, r) -> Double.valueOf(l.toString()) >= Double.valueOf(r.toString())),
    EQ("==", 1, true, Expressions::eq, (l, r) -> Double.valueOf(l.toString()).equals(Double.valueOf(r.toString()))),
    NEQ("!=", 1, true, Expressions::nEq, (l, r) -> !Double.valueOf(l.toString()).equals(Double.valueOf(r.toString()))),

    AND("&&", 1, true, Expressions::and, (l, r) -> Boolean.valueOf(l.toString()) && Boolean.valueOf(r.toString())),
    OR("||", 1, true, Expressions::or, (l, r) -> Boolean.valueOf(l.toString()) || Boolean.valueOf(r.toString())),


    POWER("POW", 4, true, Expressions::pow, (l, r) -> Math.pow(Double.valueOf(l.toString()), Double.valueOf(r.toString()))),
    MIN("MIN", 4, true, Expressions::min, (l, r) -> Math.min(Double.valueOf(l.toString()), Double.valueOf(r.toString()))),


    DATE_DIFFERENCE("DATE_DIFF", 4, true, Expressions::dateDifference, (l, r) -> ChronoUnit.DAYS.between((LocalDate) r, (LocalDate) l)),
    ADD_DAYS_TO_DATE("ADD_DAYS_TO_DATE", 4, true, Expressions::addDays, (l, r) -> ((LocalDate) l).plusDays(((Double) r).intValue())),
    SUBTRACT_DAYS_FROM_DATE("SUBTRACT_DAYS_FROM_DATE", 4, true, Expressions::subtractDays, (l, r) -> ((LocalDate) l).minusDays(((Double) r).intValue()));

    private final String symbol;
    private final int precedence;
    private final boolean isLeftAssociative;
    private final BiFunction<Expression, Expression, Expression> exprBuilderFunction;
    private final BiFunction<Object, Object, Object> evaluationFunction;

    BinaryOperator(String symbol, int precedence, boolean isLeftAssociative, BiFunction<Expression, Expression, Expression> exprBuilderFunction, BiFunction<Object, Object, Object> evaluationFunction) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
        this.exprBuilderFunction = exprBuilderFunction;
        this.evaluationFunction = evaluationFunction;
    }

    private static Object performAddition(Object l, Object r) {
        try {
            return Double.valueOf(l.toString()) + Double.valueOf(r.toString());
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

package com.evaluator.operator;

import com.evaluator.expression.Expression;
import com.evaluator.factory.Expressions;

import java.util.function.Function;

/**
 * @author shrikrushna on 2020-04-22
 */
public enum UnaryOperator implements Operator {

    FLOOR("FLOOR", 4, true, Expressions::floor, (l) -> Math.floor(Double.valueOf(l.toString()))),
    CEILING("CEIL", 4, true, Expressions::ceil, (l) -> Math.ceil(Double.valueOf(l.toString()))),
    NOT("!", 4, true, Expressions::not, (l) -> !Boolean.valueOf(l.toString()));

    private final String symbol;
    private final int precedence;
    private final boolean isLeftAssociative;
    private final Function<Expression, Expression> exprBuilderFunction;
    private final Function<Object, Object> evaluationFunction;

    UnaryOperator(String symbol, int precedence, boolean isLeftAssociative, Function<Expression, Expression> exprBuilderFunction, Function<Object, Object> evaluationFunction) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
        this.exprBuilderFunction = exprBuilderFunction;
        this.evaluationFunction = evaluationFunction;
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

    public Function<Expression, Expression> getExprBuilderFunction() {
        return exprBuilderFunction;
    }

    public Function<Object, Object> getEvaluationFunction() {
        return evaluationFunction;
    }
}

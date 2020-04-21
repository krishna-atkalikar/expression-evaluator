package com.evaluator.operator;

import com.evaluator.expression.Expression;
import com.evaluator.factory.Expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author shrikrushna on 2020-04-22
 */
public enum UnaryOperator implements Operator {
    FLOOR("FLOOR", 4, true, Expressions::floor, (l) -> Math.floor(Double.valueOf(l.toString()))),
    CEILING("CEIL", 4, true, Expressions::ceil, (l) -> Math.ceil(Double.valueOf(l.toString()))),
    NOT("!", 4, true, Expressions::not, (l) -> !Boolean.valueOf(l.toString())),
    ;

    private static final Map<String, UnaryOperator> map = new HashMap<>();

    static {
        for (UnaryOperator value : values()) {
            map.put(value.getSymbol(), value);
        }
    }

    private final int precedence;
    private final boolean isLeftAssociative;
    private final Function<Expression, Expression> exprBuilderFunction;
    private final Function<Object, Object> evaluationFunction;
    private String symbol;

    UnaryOperator(String symbol, int precedence, boolean isLeftAssociative, Function<Expression, Expression> exprBuilderFunction, Function<Object, Object> evaluationFunction) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
        this.exprBuilderFunction = exprBuilderFunction;
        this.evaluationFunction = evaluationFunction;
    }

    public static UnaryOperator from(String symbol) {
        return map.get(symbol);
    }

    public static boolean contains(String symbol) {
        return map.containsKey(symbol);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public int getPrecedence() {
        return precedence;
    }

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

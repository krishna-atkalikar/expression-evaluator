package com.evaluator.operator;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Operators {

    private static final Map<BinaryOperator, BiFunction<Object, Object, Object>> binaryOperatorToEvaluationFunction;
    private static final Map<UnaryOperator, Function<Object, Object>> unaryOperatorToEvaluationFunction;
    private static final Map<String, Operator> symbolToOperatorMap;

    static {
        symbolToOperatorMap = Stream.of(stream(BinaryOperator.values()), stream(UnaryOperator.values()), stream(TernaryOperator.values()))
                .flatMap(Function.identity())
                .collect(Collectors.toMap(op -> op.getSymbol(), op -> op));

        binaryOperatorToEvaluationFunction = stream(BinaryOperator.values())
                .collect(Collectors.toMap(op -> op, BinaryOperator::getEvaluationFunction));

        unaryOperatorToEvaluationFunction = stream(UnaryOperator.values())
                .collect(Collectors.toMap(op -> op, UnaryOperator::getEvaluationFunction));
    }

    public static boolean isOperator(String symbol) {
        return symbolToOperatorMap.containsKey(symbol);
    }

    public static boolean isLeftAssociative(String symbol) {
        return symbolToOperatorMap.get(symbol).isLeftAssociative();
    }

    public static int getPrecedence(String symbol) {
        return symbolToOperatorMap.get(symbol).getPrecedence();
    }

    public static BiFunction<Object, Object, Object> get(BinaryOperator type) {
        return binaryOperatorToEvaluationFunction.get(type);
    }

    public static Function<Object, Object> get(UnaryOperator unaryOperator) {
        return unaryOperatorToEvaluationFunction.get(unaryOperator);
    }

    public static boolean isBinary(String symbol) {
        return symbolToOperatorMap.get(symbol) instanceof BinaryOperator;
    }

    public static boolean isUnary(String symbol) {
        return symbolToOperatorMap.get(symbol) instanceof UnaryOperator;
    }

    public static boolean isTernary(String symbol) {
        return symbolToOperatorMap.get(symbol) instanceof TernaryOperator;
    }
}

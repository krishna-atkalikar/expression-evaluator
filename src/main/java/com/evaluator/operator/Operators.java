package com.evaluator.operator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Operators {

    private static final EnumMap<OperationType, BiFunction<Object, Object, Object>> binaryOperators = new EnumMap<>(OperationType.class);
    private static final EnumMap<OperationType, Function<Object, Object>> unaryFunction = new EnumMap<>(OperationType.class);
    private static final Map<String, Operator> symbolToOperatorMap = new HashMap<>();

    static {
        binaryOperators.put(OperationType.ADD, (l, r) -> {
            try {
                return Double.valueOf(l.toString()) + Double.valueOf(r.toString());
            } catch (Exception ex) {
                return l.toString() + r.toString();
            }
        });
        binaryOperators.put(OperationType.SUBTRACT, (l, r) -> Double.valueOf(l.toString()) - Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.MULTIPLICATION, (l, r) -> Double.valueOf(l.toString()) * Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.DIVISION, (l, r) -> Double.valueOf(l.toString()) / Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.POWER, (l, r) -> Math.pow(Double.valueOf(l.toString()), Double.valueOf(r.toString())));

        binaryOperators.put(OperationType.DATE_DIFFERENCE, (l, r) -> ChronoUnit.DAYS.between((LocalDate) r, (LocalDate) l));
        binaryOperators.put(OperationType.ADD_DAYS_TO_DATE, (l, r) -> ((LocalDate) l).plusDays(((Double) r).intValue()));
        binaryOperators.put(OperationType.SUBTRACT_DAYS_FROM_DATE, (l, r) -> ((LocalDate) l).minusDays(((Double) r).intValue()));

        binaryOperators.put(OperationType.LT, (l, r) -> Double.valueOf(l.toString()) < Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.LTE, (l, r) -> Double.valueOf(l.toString()) <= Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.GT, (l, r) -> Double.valueOf(l.toString()) > Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.GTE, (l, r) -> Double.valueOf(l.toString()) >= Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.EQ, (l, r) -> Double.valueOf(l.toString()).equals(Double.valueOf(r.toString())));
        binaryOperators.put(OperationType.NEQ, (l, r) -> !Double.valueOf(l.toString()).equals(Double.valueOf(r.toString())));

        binaryOperators.put(OperationType.AND, (l, r) -> Boolean.valueOf(l.toString()) && Boolean.valueOf(r.toString()));
        binaryOperators.put(OperationType.OR, (l, r) -> Boolean.valueOf(l.toString()) || Boolean.valueOf(r.toString()));
        binaryOperators.put(OperationType.MIN, (l, r) -> Math.min(Double.valueOf(l.toString()), Double.valueOf(r.toString())));

        unaryFunction.put(OperationType.FLOOR, (l) -> Math.floor(Double.valueOf(l.toString())));
        unaryFunction.put(OperationType.CEILING, (l) -> Math.ceil(Double.valueOf(l.toString())));
        unaryFunction.put(OperationType.NOT, (l) -> !Boolean.valueOf(l.toString()));
    }

    public static BiFunction<Object, Object, Object> getBinary(OperationType type) {
        return binaryOperators.get(type);
    }

    public static Function<Object, Object> getUnary(OperationType type) {
        return unaryFunction.get(type);
    }
}

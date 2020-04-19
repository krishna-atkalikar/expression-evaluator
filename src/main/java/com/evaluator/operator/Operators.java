package com.evaluator.operator;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.EnumMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Operators {

    private static final EnumMap<OperationType, BiFunction<Object, Object, Object>> binaryOperators = new EnumMap<>(OperationType.class);
    private static final EnumMap<OperationType, Function<Object, Object>> unaryFunction = new EnumMap<>(OperationType.class);


    static {
        binaryOperators.put(OperationType.ADD, (l, r) -> Double.valueOf(l.toString()) + Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.SUBTRACT, (l, r) -> Double.valueOf(l.toString()) - Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.MULTIPLICATION, (l, r) -> Double.valueOf(l.toString()) * Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.DIVISION, (l, r) -> Double.valueOf(l.toString()) / Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.POWER, (l, r) -> Math.pow(Double.valueOf(l.toString()), Double.valueOf(r.toString())));

        binaryOperators.put(OperationType.DATE_DIFFERENCE, (l, r) -> TimeUnit.DAYS.toDays(((Date) l).getTime() - ((Date) r).getTime()));
        binaryOperators.put(OperationType.ADD_DAYS_TO_DATE, (l, r) -> new DateTime(l).plusDays(Integer.valueOf(r.toString())).toDate());
        binaryOperators.put(OperationType.SUBTRACT_DAYS_TO_DATE, (l, r) -> new DateTime(l).minusDays(Integer.valueOf(r.toString())).toDate());

        binaryOperators.put(OperationType.LT, (l, r) -> Double.valueOf(l.toString()) < Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.GT, (l, r) -> Double.valueOf(l.toString()) > Double.valueOf(r.toString()));
        binaryOperators.put(OperationType.EQ, (l, r) -> Double.valueOf(l.toString()).equals(Double.valueOf(r.toString())));
        binaryOperators.put(OperationType.NEQ, (l, r) -> !Double.valueOf(l.toString()).equals(Double.valueOf(r.toString())));

        binaryOperators.put(OperationType.AND, (l, r) -> !Boolean.valueOf(l.toString()) && Boolean.valueOf(r.toString()));
        binaryOperators.put(OperationType.OR, (l, r) -> !Boolean.valueOf(l.toString()) || Boolean.valueOf(r.toString()));

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

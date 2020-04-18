package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Subtraction extends Operator {


    public Integer doIntegerSubtraction(Integer left, Integer right) {
        return left - right;
    }

    public Double doDoubleSubtraction(Double left, Double right) {
        return left - right;
    }
}

package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class GreaterThan extends Operator {

    public boolean doIntegerGreaterThan(Integer left, Integer right) {
        return left > right;
    }

    public boolean doDoubleGreaterThan(Double left, Double right) {
        return left > right;
    }

}

package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Min extends Operator {

    public Double doDoubleMin(Double left, Double right) {
        return Math.min(left, right);
    }

}

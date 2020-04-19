package com.evaluator.operator.function;

import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Min extends Operator {

    public Double doDoubleMin(Double left, Double right) {
        return Math.min(left, right);
    }

}

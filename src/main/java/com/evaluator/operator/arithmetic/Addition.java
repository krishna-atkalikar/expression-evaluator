package com.evaluator.operator.arithmetic;

import com.evaluator.operator.Operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Addition extends Operator {

    public Integer doIntegerAddition(Integer left, Integer right) {
        return left + right;
    }

    public Integer doIntegerAddition(Integer left, Double right) {
        return left + right.intValue();
    }

    public Double doDoubleAddition(Double left, Double right) {
        return left + right;
    }

    public Double doDoubleAddition(Double left, Integer right) {
        return left + right;
    }

    public String doStringAddition(String left, String right) {
        return left + right;
    }
}

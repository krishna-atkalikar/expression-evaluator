package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Addition extends Operator {

    public Double doAddition(Object left, Object right) {
        return Double.valueOf(left.toString()) + Double.valueOf(right.toString());
    }

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

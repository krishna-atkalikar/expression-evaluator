package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Division extends Operator {


    public Integer doIntegerDivision(Integer left, Integer right) {
        return left / right;
    }

    public Double doDoubleDivision(Double left, Double right) {
        return left / right;
    }
}

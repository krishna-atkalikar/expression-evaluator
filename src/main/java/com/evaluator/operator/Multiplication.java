package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public class Multiplication extends Operator {


    public Integer doIntegerMultiplication(Integer left, Integer right) {
        return left * right;
    }

    public Double doDoubleMultiplication(Double left, Double right) {
        return left * right;
    }

}

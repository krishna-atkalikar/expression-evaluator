package com;

/**
 * @author shrikrushna on 2020-04-11
 */
public abstract class Operator {

    public abstract double apply(double left, double right);

    public static class Addition extends Operator {

        @Override
        public double apply(double left, double right) {
            return left + right;
        }
    }

    public static class Subtraction extends Operator {

        @Override
        public double apply(double left, double right) {
            return left - right;
        }
    }
}

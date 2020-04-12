package com;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ConstantExpression extends Expression {

    private double value;

    public ConstantExpression(double value) {
        this.value = value;
    }

    @Override
    public double eval(ExpressionContext context) {
        return value;
    }
}

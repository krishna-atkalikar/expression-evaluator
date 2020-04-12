package com;

/**
 * @author shrikrushna on 2020-04-11
 */
public class TernaryExpression extends Expression {


    private Expression condition;
    private Expression then;
    private Expression orElse;

    @Override
    public double eval(ExpressionContext context) {
        return 0;
    }

    public static class IF extends TernaryExpression {


    }

}

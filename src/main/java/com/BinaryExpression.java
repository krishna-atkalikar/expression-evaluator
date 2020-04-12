package com;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpression extends Expression {

    private Operator op;
    private Expression left;
    private Expression right;

    public BinaryExpression(Operator op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(ExpressionContext context) {
        double left = this.left.eval(context);
        double right = this.right.eval(context);
        return op.apply(left, right);
    }
}

package com;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ExpressionTest {

    @Test
    public void testSimpleBinaryExpression() {
        BinaryExpression binaryExpression = new BinaryExpression(new Operator.Addition(), new ConstantExpression(1), new ConstantExpression(2));
        double eval = binaryExpression.eval(new ExpressionContext());
        assertEquals(3.0, eval, 0.0);
    }

    @Test
    public void testTwoBinaryExpressions() {
        BinaryExpression binaryExpression = new BinaryExpression(new Operator.Subtraction()
                , new BinaryExpression(new Operator.Addition(), new ConstantExpression(4), new ConstantExpression(5))
                , new BinaryExpression(new Operator.Addition(), new ConstantExpression(2), new ConstantExpression(2)));
        double eval = binaryExpression.eval(new ExpressionContext());
        assertEquals(5.0, eval, 0.0);

    }
}

package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import static com.evaluator.util.Expressions.*;
import static org.junit.Assert.*;

/**
 * @author shrikrushna on 2020-04-19
 */
public class UnaryExpressionTest {

    @Test
    public void notReturnsOpposite() {
        Expression expr = not(constant(false));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(expr));
    }

    @Test
    public void notReturnsOpposite_withLogicalExpression() {
        Expression expr = not(lt(constant(5), constant(6)));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertFalse(visitor.visit(expr));
    }

    @Test
    public void floorReturns_floorValue() {
        Expression expr = floor(constant(5.6));

        Visitor<Double> visitor = new ExpressionVisitor<>();

        assertEquals((Double) 5.0, visitor.visit(expr));
    }

    @Test
    public void ceilReturns_ceilValue() {
        Expression expr = ceil(constant(5.4));

        Visitor<Double> visitor = new ExpressionVisitor<>();

        assertEquals((Double) 6.0, visitor.visit(expr));
    }

}

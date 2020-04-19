package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import static com.evaluator.factory.Expressions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author shrikrushna on 2020-04-19
 */
public class LogicalExpressionTest {

    @Test
    public void lt_returnsTrue() {
        Expression expr = lt(constant(5), constant(6));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(expr));
    }

    @Test
    public void gt_returnsTrue() {
        Expression expr = gt(constant(6), constant(5));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(expr));
    }

    @Test
    public void eq_returnsTrue() {
        Expression expr = eq(constant(6), constant(6));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(expr));
    }

    @Test
    public void neq_returnsTrue() {
        Expression expr = nEq(constant(5), constant(6));

        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(expr));
    }

    @Test
    public void andOperations() {
        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(and(constant(true), constant(true))));
        assertFalse(visitor.visit(and(constant(false), constant(true))));
        assertFalse(visitor.visit(and(constant(true), constant(false))));
        assertFalse(visitor.visit(and(constant(false), constant(false))));
    }

    @Test
    public void andComposedWithLogicalOperators() {
        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(and(lt(constant(5), constant(6)), gt(constant(8), constant(5)))));
    }

    @Test
    public void orOperations() {
        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertTrue(visitor.visit(or(constant(true), constant(true))));
        assertTrue(visitor.visit(or(constant(false), constant(true))));
        assertTrue(visitor.visit(or(constant(true), constant(false))));
        assertFalse(visitor.visit(or(constant(false), constant(false))));
    }

    @Test
    public void orComposedWithLogicalOperators() {
        Visitor<Boolean> visitor = new ExpressionVisitor<>();

        assertFalse(visitor.visit(or(lt(constant(6), constant(5)), gt(constant(5), constant(7)))));
    }
}

package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Assert;
import org.junit.Test;

import static com.evaluator.Expressions.*;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ComplexExpressionTest {

    //IF(Salary <= 250000, 0, MIN(250000, (Salary - 250000)) * 5 / 100)
    @Test
    public void slab1Test() {
        evaluateAndAssertSlab1(250000.0d, 250000.0d, 0.0d);
        evaluateAndAssertSlab1(400000.0d, 250000.0d, 7500.0d);
        evaluateAndAssertSlab1(600000.0d, 250000.0d, 12500.0d);
    }

    private void evaluateAndAssertSlab1(Double salary, Double slab, Double expected) {
        Expression expr = iff(lte(constant(salary), constant(slab))
                , constant(0)
                , multiply(min(constant(slab), sub(constant(salary), constant(slab))), divide(constant(5), constant(100))));

        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(expr);

        Assert.assertEquals(expected, result);
    }

    //Slab2 = IF(Salary > 500000, MIN(500000, (Salary - 500000)) * 20 / 100, 0)
    @Test
    public void slab2Test() {
        evaluateAndAssertSlab2(400000.0d, 500000.0d, 0.0d);
        evaluateAndAssertSlab2(800000.0d, 500000.0d, 60000.0d);
        evaluateAndAssertSlab2(1100000.0d, 500000.0d, 100000.0d);
    }

    private void evaluateAndAssertSlab2(Double salary, Double slab, Double expected) {
        Expression expr = iff(gt(constant(salary), constant(slab))
                , multiply(min(constant(slab), sub(constant(salary), constant(slab))), divide(constant(20), constant(100)))
                , constant(0));

        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(expr);

        Assert.assertEquals(expected, result);
    }

    //Slab3 = IF(Salary > 1000000, (Salary - 1000000) * 30 / 100, 0)
    @Test
    public void slab3Test() {
        evaluateAndAssertSlab3(800000.0d, 1000000.0d, 0.0d);
        evaluateAndAssertSlab3(1400000.0d, 1000000.0d, 120000.0d);
    }

    private void evaluateAndAssertSlab3(Double salary, Double slab, Double expected) {
        Expression expr = iff(gt(constant(salary), constant(slab))
                , multiply(min(constant(slab), sub(constant(salary), constant(slab))), divide(constant(30), constant(100)))
                , constant(0));

        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(expr);

        Assert.assertEquals(expected, result);
    }

}

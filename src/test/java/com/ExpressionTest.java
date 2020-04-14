package com;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.LogicalExpression;
import com.evaluator.expression.TernaryExpression;
import com.evaluator.expression.ValueExpression;
import com.evaluator.operator.arithmetic.Division;
import com.evaluator.operator.arithmetic.Min;
import com.evaluator.operator.arithmetic.Multiplication;
import com.evaluator.operator.arithmetic.Subtraction;
import com.evaluator.operator.logical.GreaterThan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ExpressionTest {

//    @Test
//    public void testSimpleBinaryExpression() {
//        BinaryExpression<Double, Double> binaryExpression = new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1.0), new ValueExpression<>(2.0));
//        double eval = binaryExpression.evaluate();
//        assertEquals(3.0, eval, 0.0);
//    }
//
//    @Test
//    public void testSimpleBooleanExpression() {
//        LogicalExpression<Integer> expression = new LogicalExpression<>(new GreaterThan<>(), new ValueExpression<>(1), new ValueExpression<>(2));
//        boolean eval = expression.evaluate();
//        assertTrue(eval);
//    }
//
//    @Test
//    public void testTwoBinaryExpressions() {
//        BinaryExpression<Integer, Integer> binaryExpression = new BinaryExpression<>(new Addition<>()
//                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2))
//                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2)));
//        Integer eval = binaryExpression.evaluate();
//        assertEquals(6.0, eval, 0.0);
//    }
//
//    @Test
//    public void testTernary() {
//        TernaryExpression<Integer, Integer> binaryExpression = new TernaryExpression<>(
//                new LogicalExpression<>(new GreaterThan<>(), new ValueExpression<>(2), new ValueExpression<>(1))
//                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(2), new ValueExpression<>(2))
//                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2)));
//        Integer eval = binaryExpression.evaluate();
//        assertEquals(4, eval, 0.0);
//    }

    //IF(Salary <= 250000, 0, MIN(250000, (Salary - 250000)) * 5 / 100)

    @Test
    public void whenSalaryIsGreaterThanSlab1ThenComputeTax() {
        double salary = 400000.00;
        TernaryExpression<Double, Double> evaluate = new TernaryExpression<>(
                new LogicalExpression<>(new GreaterThan<>(), new ValueExpression<>(250001.00), new ValueExpression<>(salary))
                , new ValueExpression<>(0.00)
                , new BinaryExpression<>(new Multiplication<>()
                , new BinaryExpression<>(new Min<>()
                , new ValueExpression<>(250000.00)
                , new BinaryExpression<>(new Subtraction<>(), new ValueExpression<>(salary), new ValueExpression<>(250000.00))
        )
                , new BinaryExpression<>(new Division<>(), new ValueExpression<>(5.00), new ValueExpression<>(100.00))
        )
        );
        double eval = evaluate.evaluate();
        assertEquals(0, eval, 0.0);
    }
}

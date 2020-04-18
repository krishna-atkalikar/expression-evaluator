package com;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.ConstantExpression;
import com.evaluator.expression.Expression;
import com.evaluator.expression.TernaryExpression;
import com.evaluator.operator.*;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ExpressionTest {

    @Test
    public void testSimpleBinaryExpression() {

        Visitor<String> visitor = new ExpressionVisitor<>();
        Expression val1 = new ConstantExpression("a");
        Expression val2 = new ConstantExpression("b");
        Expression val3 = new ConstantExpression("c");
        Expression val4 = new ConstantExpression("d");

        BinaryExpression binaryExpression1 = new BinaryExpression(new Addition(), val1, val2);
        BinaryExpression binaryExpression2 = new BinaryExpression(new Addition(), val3, val4);

        BinaryExpression binaryExpression = new BinaryExpression(new Addition(), binaryExpression1, binaryExpression2);
        String accept = binaryExpression.accept(visitor);
        System.out.println("accept = " + accept);
    }

    @Test
    public void testSimpleAddition() {
        Visitor<Double> visitor = new ExpressionVisitor<>();
        Expression val1 = new ConstantExpression(1);
        Expression val2 = new ConstantExpression(1.5);

        BinaryExpression binaryExpression1 = new BinaryExpression(new Addition(), val1, val2);

        Double accept = binaryExpression1.accept(visitor);
        System.out.println("accept = " + accept);
    }

    @Test
    public void testLogicalBinaryExpression() {

        Visitor<Boolean> visitor = new ExpressionVisitor<>();
        Expression val1 = new ConstantExpression(1);
        Expression val2 = new ConstantExpression(2);
        Expression val3 = new ConstantExpression("3");
        Expression val4 = new ConstantExpression("4");

        BinaryExpression binaryExpression1 = new BinaryExpression(new GreaterThan(), val1, val2);

        Boolean accept = binaryExpression1.accept(visitor);
        System.out.println("accept = " + accept);
    }

    @Test
    public void testTernaryExpression() {
        Visitor<Integer> visitor = new ExpressionVisitor<>();
        Expression val1 = new ConstantExpression(1);
        Expression val2 = new ConstantExpression(2);
        BinaryExpression binaryExpression1 = new BinaryExpression(new GreaterThan(), val2, val1);
        TernaryExpression ternaryExpression = new TernaryExpression(binaryExpression1, val1, val2);

        Integer accept = ternaryExpression.accept(visitor);

        System.out.println("accept = " + accept);
    }
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
        TernaryExpression evaluate = new TernaryExpression(
                new BinaryExpression(new GreaterThan(), new ConstantExpression(250001.00), new ConstantExpression(salary))
                , new ConstantExpression(0.00)
                , new BinaryExpression(new Multiplication()
                , new BinaryExpression(new Min(), new ConstantExpression(250000.00), new BinaryExpression(new Subtraction(), new ConstantExpression(salary), new ConstantExpression(250000.00)))
                , new BinaryExpression(new Division(), new ConstantExpression(5.00), new ConstantExpression(100.00)))
        );

        Visitor<Double> visitor = new ExpressionVisitor<>();
        Double visit = visitor.visit(evaluate);
        System.out.println("visit = " + visit);
//        double eval = evaluate.evaluate();
//        assertEquals(0, eval, 0.0);
    }
}

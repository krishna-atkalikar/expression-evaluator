package com;

import com.evaluator.expression.BinaryExpression;
import com.evaluator.expression.ConstantExpression;
import com.evaluator.expression.Expression;
import com.evaluator.expression.TernaryExpression;
import com.evaluator.operator.OperationType;
import com.evaluator.operator.arithmetic.Addition;
import com.evaluator.operator.arithmetic.Division;
import com.evaluator.operator.arithmetic.Multiplication;
import com.evaluator.operator.arithmetic.Subtraction;
import com.evaluator.operator.date.DateDifference;
import com.evaluator.operator.function.Min;
import com.evaluator.operator.logical.GreaterThan;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import java.util.Date;

/**
 * @author shrikrushna on 2020-04-11
 */
public class ExpressionTest {

    @Test
    public void and() {
        boolean v = false;
    }

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

        BinaryExpression binaryExpression1 = new BinaryExpression(val1, val2, OperationType.ADD);

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

    @Test
    public void testDate() {
        BinaryExpression dateExpression = new BinaryExpression(new DateDifference(), new ConstantExpression(new Date()), new ConstantExpression(1));
//        BinaryExpression dateExpression = new BinaryExpression(new ConstantExpression(new Date()), new ConstantExpression(1), (date, days) -> new DateTime(date).plusDays(Integer.valueOf(days.toString())).toDate());


        Visitor<Date> visitor = new ExpressionVisitor<>();

        Date accept = dateExpression.accept(visitor);

        System.out.println("accept = " + accept);
    }
}

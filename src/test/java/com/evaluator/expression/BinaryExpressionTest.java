package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import static com.evaluator.factory.Expressions.*;
import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-11
 */
public class BinaryExpressionTest {

    @Test
    public void additionWithTwoConstantExpression() {
        Expression binaryExpression = add(constant(1), constant(2));
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 3.0, result);
    }

    @Test
    public void subtractionWithTwoConstantExpression() {
        Expression binaryExpression = sub(constant(2), constant(1));
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 1.0, result);
    }

    @Test
    public void multiplicationWithTwoConstantExpression() {
        Expression binaryExpression = multiply(constant(2), constant(3));
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 6.0, result);
    }

    @Test
    public void divisionWithTwoConstantExpression() {
        Expression binaryExpression = divide(constant(9), constant(3));
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 3.0, result);
    }

    @Test
    public void additionWithMultipleConstantExpression() {
        Expression expr1 = add(constant(1), constant(2));
        Expression expr2 = add(constant(1), constant(2));
        Expression binaryExpression = add(expr1, expr2);
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 6.0, result);
    }

    @Test
    //(45 - 5) - (25 - 5)
    public void subtractionWithMultipleConstantExpression() {
        Expression expr1 = sub(constant(45), constant(5));
        Expression expr2 = sub(constant(25), constant(5));
        Expression binaryExpression = sub(expr1, expr2);
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Double result = visitor.visit(binaryExpression);

        assertEquals((Double) 20.0, result);
    }

    @Test
    //8 * 5 - 4 + 10 * 3 + 12 / 2
    public void complexExpression() {
        Expression eight = constant(8);
        Expression five = constant(5);
        Expression four = constant(4);
        Expression ten = constant(10);
        Expression three = constant(3);
        Expression twelve = constant(12);
        Expression two = constant(2);

        Expression expr = add(add(sub(multiply(eight, five), four), multiply(ten, three)), divide(twelve, two));

        Visitor<Double> visitor = new ExpressionVisitor<>();
        Double result = visitor.visit(expr);

        assertEquals((Double) 72.0, result);
    }

//    @Test
//    public void testSimpleAddition() {
//        Visitor<Double> visitor = new ExpressionVisitor<>();
//        Expression val1 = new ConstantExpression(1);
//        Expression val2 = new ConstantExpression(1.5);
//
//        BinaryExpression binaryExpression1 = new BinaryExpression(val1, val2, OperationType.ADD);
//
//        Double accept = binaryExpression1.accept(visitor);
//        System.out.println("accept = " + accept);
//    }
//
//    @Test
//    public void testLogicalBinaryExpression() {
//
//        Visitor<Boolean> visitor = new ExpressionVisitor<>();
//        Expression val1 = new ConstantExpression(1);
//        Expression val2 = new ConstantExpression(2);
//        Expression val3 = new ConstantExpression("3");
//        Expression val4 = new ConstantExpression("4");
//
//        BinaryExpression binaryExpression1 = new BinaryExpression(new GreaterThan(), val1, val2);
//
//        Boolean accept = binaryExpression1.accept(visitor);
//        System.out.println("accept = " + accept);
//    }
//
//    @Test
//    public void testTernaryExpression() {
//        Visitor<Integer> visitor = new ExpressionVisitor<>();
//        Expression val1 = new ConstantExpression(1);
//        Expression val2 = new ConstantExpression(2);
//        BinaryExpression binaryExpression1 = new BinaryExpression(new GreaterThan(), val2, val1);
//        TernaryExpression ternaryExpression = new TernaryExpression(binaryExpression1, val1, val2);
//
//        Integer accept = ternaryExpression.accept(visitor);
//
//        System.out.println("accept = " + accept);
//    }
////
////    @Test
////    public void testSimpleBooleanExpression() {
////        LogicalExpression<Integer> expression = new LogicalExpression<>(new GreaterThan<>(), new ValueExpression<>(1), new ValueExpression<>(2));
////        boolean eval = expression.evaluate();
////        assertTrue(eval);
////    }
////
////    @Test
////    public void testTwoBinaryExpressions() {
////        BinaryExpression<Integer, Integer> binaryExpression = new BinaryExpression<>(new Addition<>()
////                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2))
////                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2)));
////        Integer eval = binaryExpression.evaluate();
////        assertEquals(6.0, eval, 0.0);
////    }
////
////    @Test
////    public void testTernary() {
////        TernaryExpression<Integer, Integer> binaryExpression = new TernaryExpression<>(
////                new LogicalExpression<>(new GreaterThan<>(), new ValueExpression<>(2), new ValueExpression<>(1))
////                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(2), new ValueExpression<>(2))
////                , new BinaryExpression<>(new Addition<>(), new ValueExpression<>(1), new ValueExpression<>(2)));
////        Integer eval = binaryExpression.evaluate();
////        assertEquals(4, eval, 0.0);
////    }
//
//    //IF(Salary <= 250000, 0, MIN(250000, (Salary - 250000)) * 5 / 100)
//
//    @Test
//    public void whenSalaryIsGreaterThanSlab1ThenComputeTax() {
//        double salary = 400000.00;
//        TernaryExpression evaluate = new TernaryExpression(
//                new BinaryExpression(new GreaterThan(), new ConstantExpression(250001.00), new ConstantExpression(salary))
//                , new ConstantExpression(0.00)
//                , new BinaryExpression(new Multiplication()
//                , new BinaryExpression(new Min(), new ConstantExpression(250000.00), new BinaryExpression(new Subtraction(), new ConstantExpression(salary), new ConstantExpression(250000.00)))
//                , new BinaryExpression(new Division(), new ConstantExpression(5.00), new ConstantExpression(100.00)))
//        );
//
//        Visitor<Double> visitor = new ExpressionVisitor<>();
//        Double visit = visitor.visit(evaluate);
//        System.out.println("visit = " + visit);
////        double eval = evaluate.evaluate();
////        assertEquals(0, eval, 0.0);
//    }
//
//    @Test
//    public void testDate() {
//        BinaryExpression dateExpression = new BinaryExpression(new DateDifference(), new ConstantExpression(new Date()), new ConstantExpression(1));
////        BinaryExpression dateExpression = new BinaryExpression(new ConstantExpression(new Date()), new ConstantExpression(1), (date, days) -> new DateTime(date).plusDays(Integer.valueOf(days.toString())).toDate());
//
//
//        Visitor<Date> visitor = new ExpressionVisitor<>();
//
//        Date accept = dateExpression.accept(visitor);
//
//        System.out.println("accept = " + accept);
//    }
}

package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import static com.evaluator.util.Expressions.*;
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
}

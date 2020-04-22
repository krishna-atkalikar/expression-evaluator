package com.evaluator.expression;

import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import org.junit.Test;

import java.time.LocalDate;

import static com.evaluator.factory.Expressions.*;
import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-19
 */
public class FunctionTest {

    @Test
    public void min_returnsMinimumValueOfTwo() {
        Visitor<Double> visitor = new ExpressionVisitor<>();

        assertEquals((Double) 1.0, visitor.visit(min(constant(1), constant(2))));
    }

    @Test
    public void min_withCompositionOfOtherExpr() {
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Expression min = min(add(constant(1), constant(2)), add(constant(10), constant(20)));
        assertEquals((Double) 3.0, visitor.visit(min));
    }

    @Test
    public void powerReturnsPowerValueOfLeftOperand() {
        Visitor<Double> visitor = new ExpressionVisitor<>();

        Expression min = pow(add(constant(1), constant(2)), add(constant(1), constant(2)));
        assertEquals((Double) 27.0, visitor.visit(min));
    }

    @Test
    public void dateDifference_returnsDifferenceInDays() {
        Visitor<Long> visitor = new ExpressionVisitor<>();

        long difference = visitor.visit(dateDifference(constant(LocalDate.now()), constant(LocalDate.now().minusDays(2))));
        assertEquals(2, difference);
    }

    @Test
    public void addDaysToDate_returnsDateWithDaysAdded() {
        Visitor<LocalDate> visitor = new ExpressionVisitor<>();

        LocalDate date = visitor.visit(addDays(constant(LocalDate.now().minusDays(2)), constant(2)));
        assertEquals(LocalDate.now(), date);
    }

    @Test
    public void subtractDaysFromDate_returnsDateWithDaysAdded() {
        Visitor<LocalDate> visitor = new ExpressionVisitor<>();

        LocalDate date = visitor.visit(subtractDays(constant(LocalDate.now().plusDays(2)), constant(2)));
        assertEquals(LocalDate.now(), date);
    }

}

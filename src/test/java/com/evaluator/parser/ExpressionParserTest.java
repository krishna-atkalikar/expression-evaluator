package com.evaluator.parser;

import com.evaluator.expression.Expression;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author shrikrushna on 2020-04-21
 */
public class ExpressionParserTest {

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab1() {
        String expr = "IF ( ( Salary <= 250000 ) , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )";
        Expression expression = ExpressionParser.parse(expr);

        assertResult(expression, 0.0d, ImmutableMap.of("Salary", 250000));
        assertResult(expression, 7500.0d, ImmutableMap.of("Salary", 400000));
        assertResult(expression, 12500.0d, ImmutableMap.of("Salary", 600000));

    }

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab2() {
        String expr = "IF ( ( Salary > 500000 ) , ( MIN ( 500000 , ( Salary - 500000 ) ) * 20 / 100 ) , 0 )";
        Expression expression = ExpressionParser.parse(expr);

        assertResult(expression, 0.0d, ImmutableMap.of("Salary", 400000));
        assertResult(expression, 60000.0d, ImmutableMap.of("Salary", 800000));
        assertResult(expression, 100000.0d, ImmutableMap.of("Salary", 1100000));

    }

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab3() {
        String expr = "IF ( ( Salary > 1000000 ) , ( ( Salary - 1000000 ) * 30 / 100 ) , 0 )";
        Expression expression = ExpressionParser.parse(expr);

        assertResult(expression, 0.0d, ImmutableMap.of("Salary", 800000));
        assertResult(expression, 120000.0d, ImmutableMap.of("Salary", 1400000));

    }

    @Test
    public void evaluateSimpleGreaterThanExpression() {
        String expr = "2 > 3";
        Expression expression = ExpressionParser.parse(expr);
        Visitor<Boolean> visitor = new ExpressionVisitor<>(ImmutableMap.of("Salary", 250000));

        assertFalse(visitor.visit(expression));
    }

    @Test
    public void evaluateDateExpression() {
        String expr = "DATE_DIFF ( 14-04-2020 , 12-04-2020 )";
        Expression expression = ExpressionParser.parse(expr);

        Visitor<Long> visitor = new ExpressionVisitor<>(ImmutableMap.of("Salary", 250000));

        assertEquals((Long) 2L, visitor.visit(expression));
    }

    private void assertResult(Expression expression, Double expected, ImmutableMap<String, Object> paramToValueMap) {
        Visitor<Double> visitor = new ExpressionVisitor<>(paramToValueMap);
        Double visit = visitor.visit(expression);
        assertEquals(expected, visit);
    }
}

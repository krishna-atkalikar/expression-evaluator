package com.evaluator.parser;

import com.evaluator.ExpressionEvaluator;
import com.evaluator.InvalidDataSetException;
import com.evaluator.expression.Expression;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.evaluator.factory.Expressions.constant;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author shrikrushna on 2020-04-21
 */
public class ExpressionEvaluatorTest {

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab1() {
        String expr = "IF ( ( $Salary$ <= 250000 ) , 0.0 , MIN ( 250000.0 , ( $Salary$ - 250000.0 ) ) * 5.0 / 100.0 )";

        assertResult(expr, 0.0d, ImmutableMap.of("Salary", constant(250000)));
        assertResult(expr, 7500.0d, ImmutableMap.of("Salary", constant(400000)));
        assertResult(expr, 12500.0d, ImmutableMap.of("Salary", constant(600000)));

    }

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab2() {
        String expr = "IF ( ( $Salary$ > 500000 ) , ( MIN ( 500000 , ( $Salary$ - 500000 ) ) * 20 / 100 ) , 0 )";

        assertResult(expr, 0.0d, ImmutableMap.of("Salary", constant(400000)));
        assertResult(expr, 60000.0d, ImmutableMap.of("Salary", constant(800000)));
        assertResult(expr, 100000.0d, ImmutableMap.of("Salary", constant(1100000)));

    }

    @Test
    public void evaluateTernaryExpression_withDifferentDataSet_forSlab3() {
        String expr = "IF ( ( $Salary$ > 1000000 ) , ( ( $Salary$ - 1000000 ) * 30 / 100 ) , 0 )";

        assertResult(expr, 0.0d, ImmutableMap.of("Salary", constant(800000)));
        assertResult(expr, 120000.0d, ImmutableMap.of("Salary", constant(1400000)));

    }

    @Test
    public void totalTaxTest() {
        ExpressionEvaluator<Double> expressionEvaluator = new ExpressionEvaluator<>();

        Expression slab1Exp = ExpressionBuilder.build("IF ( ( $Salary$ <= 250000 ) , 0.0 , MIN ( 250000.0 , ( $Salary$ - 250000.0 ) ) * 5.0 / 100.0 )");
        Expression slab2Expr = ExpressionBuilder.build("IF ( ( $Salary$ > 500000 ) , ( MIN ( 500000 , ( $Salary$ - 500000 ) ) * 20 / 100 ) , 0 )");
        Expression slab3Expr = ExpressionBuilder.build("IF ( ( $Salary$ > 1000000 ) , ( ( $Salary$ - 1000000 ) * 30 / 100 ) , 0 )");
        Expression cessExpr = ExpressionBuilder.build("( $Slab1$ + $Slab2$ + $Slab3$ ) * 4 / 100");

        String expr = "$Slab1$ + $Slab2$ + $Slab3$ + $Cess$";

        assertEquals((Double) 241800.0, expressionEvaluator.evaluate(expr, ImmutableMap.of("Slab1", slab1Exp,
                "Slab2", slab2Expr,
                "Slab3", slab3Expr,
                "Salary", constant(1400000),
                "Cess", cessExpr)));
    }

    @Test(expected = InvalidDataSetException.class)
    public void incompleteDataSet_throwsInvalidDataSetException() {
        ExpressionEvaluator<Boolean> expressionEvaluator = new ExpressionEvaluator<>();
        Boolean result = expressionEvaluator.evaluate("$Salary$ > 10 + 5", ImmutableMap.of("baseSalary", constant(25)));
    }

    @Test
    public void evaluateSimpleGreaterThanExpression() {
        ExpressionEvaluator<Boolean> expressionEvaluator = new ExpressionEvaluator<>();
        Boolean result = expressionEvaluator.evaluate("2 > 3");

        assertFalse(result);
    }

    @Test
    public void evaluateDateExpression() {
        ExpressionEvaluator<Long> expressionEvaluator = new ExpressionEvaluator<>();
        Long result = expressionEvaluator.evaluate("DATE_DIFF ( 14-04-2020 , 12-04-2020 )");

        assertEquals((Long) 2L, result);
    }

    private void assertResult(String expr, Double expected, ImmutableMap<String, Expression> paramToValueMap) {
        ExpressionEvaluator<Double> expressionEvaluator = new ExpressionEvaluator<>();
        Double result = expressionEvaluator.evaluate(expr, paramToValueMap);
        assertEquals(expected, result);
    }
}

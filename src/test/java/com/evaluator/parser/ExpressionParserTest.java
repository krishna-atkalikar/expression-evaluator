package com.evaluator.parser;

import com.evaluator.parser.token.Token;
import org.junit.Test;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-20
 */
public class ExpressionParserTest {

    @Test
    public void simpleBinaryExpression() {
        String expr = "1 + 2";
        assertEquals("1 2 +", getTokenAsString(expr));
    }

    @Test
    public void simpleExpressionWithTwoOperator() {
        assertEquals("1 2 3 * +", getTokenAsString("1 + 2 * 3"));
    }

    @Test
    public void simpleExpressionWithTwoOperatorAndParenthesis() {
        assertEquals("1 2 + 3 *", getTokenAsString("( 1 + 2 ) * 3"));
    }

    @Test
    public void expressionWithFunction() {
        assertEquals("1 3 3 * + 2 3 POW +", getTokenAsString("1 + 3 * 3 + POW ( 2 , 3 )"));
    }

    @Test
    public void complexExpressionWithFunction() {
        assertEquals("250000.0 Salary 250000.0 - MIN 5.0 * 100.0 /", getTokenAsString("MIN ( 250000.0 , ( $Salary$ - 250000.0 ) ) * 5.0 / 100.0"));
    }

    @Test
    public void complexExpressionWithTernaryExpression() {
        String input = "IF ( ( $Salary$ <= 250000.0 ) , 0.0 , MIN ( 250000.0 , ( $Salary$ - 250000.0 ) ) * 5.0 / 100.0 )";
        String expected = "Salary 250000.0 <= 0.0 250000.0 Salary 250000.0 - MIN 5.0 * 100.0 / IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void testSlab2Expression() {
        String input = "IF ( ( $Salary$ > 500000 ) , ( MIN ( 500000 , ( $Salary$ - 500000 ) ) * 20 / 100 ) , 0 )";
        String expected = "Salary 500000 > 500000 Salary 500000 - MIN 20 * 100 / 0 IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void slab3ExpressionTest() {
        String input = "IF ( ( $Salary$ > 1000000 ) , ( ( $Salary$ - 1000000 ) * 30 / 100 ) , 0 )";
        String expected = "Salary 1000000 > Salary 1000000 - 30 * 100 / 0 IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void arithmeticWithLogicalExpression() {
        String input = "$Salary$ < 10 + 5";
        String expected = "Salary 10 5 + <";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void dateFunction() {
        String input = "DATE_DIFF ( 12-04-2020 , 12-04-2020 )";
        String expected = "12-04-2020 12-04-2020 DATE_DIFF";

        assertEquals(expected, getTokenAsString(input));
    }

    private String getTokenAsString(String expr) {
        return ExpressionParser.parse(expr).stream()
                .map(Token::toString)
                .collect(joining(" "));
    }
}

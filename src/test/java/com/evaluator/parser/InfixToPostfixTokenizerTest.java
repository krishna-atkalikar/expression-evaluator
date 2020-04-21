package com.evaluator.parser;

import com.evaluator.parser.token.Token;
import org.junit.Test;

import static com.evaluator.parser.InfixToPostfixTokenizer.tokenize;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-20
 */
public class InfixToPostfixTokenizerTest {

    public static void main(String[] args) {


        try {
//            String s = convert("1 + 3 * 3 + pow ( 2 , 3 )");
//            System.out.println("s = " + s);
//            LinkedList<Token> tokens = tokenizer.tokenize(" (1 - var_12) * sin(x) ");
//            LinkedList<Token> tokens = tokenizer.tokenize(" 1 + pow(1, 2)");
//            LinkedList<Token> tokens = tokenizer.tokenize(" IF(Salary <= 250000, 0, MIN(250000, (Salary - 250000)) * 5 / 100)");
//            LinkedList<Token> tokens = tokenizer.tokenize(" 1 var 3 * + var 3 pow +");
//            String s = infixToPostfix("1 + 3 * 3 + pow ( 2 , 3 )");
//            String s = infixToPostfix("( 2 + 2 ) * 2");
//            String s = infixToPostfix("IF ( Salary <= 250000.0 , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )");
//    Salary  250000.0 <= 0.0 250000 salary 250000.0 - MIN 5.0 * 100.0 / IF
//            String s = infixToPostfix("MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0");
//            String s1 = "250000 salary 250000.0 - MIN 5.0 * 100.0 /";
//    250000 salary 250000.0 - MIN 5.0 * 100.0 /
//
//            String s = infixToPostfix("IF ( Salary <= 250000.0 , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )");
//            String s = "Salary  250000 <= 0 250000 Salary 250000 - MIN 5 * 100 / IF";
//            LinkedList<Token> tokens = tokenizer.tokenize(s);
////
//            Expression make = ExpressionBuilder.make(tokens);
//            Visitor<Double> visitor = new ExpressionVisitor<>(ImmutableMap.of("Salary", 250000));
//            Double visit = visitor.visit(make);
//            System.out.println("visit = " + visit);
//            visitor = new ExpressionVisitor<>(ImmutableMap.of("Salary", 400000));
//             visit = visitor.visit(make);
//            System.out.println("visit = " + visit);
//            visitor = new ExpressionVisitor<>(ImmutableMap.of("Salary", 600000));
//             visit = visitor.visit(make);
//            System.out.println("visit = " + visit);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void simpleBinaryExpression() {
        String expr = "1 + 2";
        assertEquals("1 2 +", getTokenAsString(expr));
    }

    private String getTokenAsString(String expr) {
        return tokenize(expr).stream()
                .map(Token::toString)
                .collect(joining(" "));
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
        assertEquals("1 3 3 * + 2 3 pow +", getTokenAsString("1 + 3 * 3 + pow ( 2 , 3 )"));
    }

    @Test
    public void complexExpressionWithFunction() {
        assertEquals("250000.0 Salary 250000.0 - MIN 5.0 * 100.0 /", getTokenAsString("MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0"));
    }

    @Test
    public void complexExpressionWithTernaryExpression() {
        String input = "IF ( ( Salary <= 250000.0 ) , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )";
        String expected = "Salary 250000.0 <= 0.0 250000.0 Salary 250000.0 - MIN 5.0 * 100.0 / IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void testSlab2Expression() {
        String input = "IF ( ( Salary > 500000 ) , ( MIN ( 500000 , ( Salary - 500000 ) ) * 20 / 100 ) , 0 )";
        String expected = "Salary 500000 > 500000 Salary 500000 - MIN 20 * 100 / 0 IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void slab3ExpressionTest() {
        String input = "IF ( ( Salary > 1000000 ) , ( ( Salary - 1000000 ) * 30 / 100 ) , 0 )";
        String expected = "Salary 1000000 > Salary 1000000 - 30 * 100 / 0 IF";

        assertEquals(expected, getTokenAsString(input));
    }

    @Test
    public void arithmeticWithLogcalExpression() {
        String input = "Salary < 10 + 5";
        String expected = "Salary 10 5 + <";

        assertEquals(expected, getTokenAsString(input));
    }


    @Test
    public void dateFunction() {
        String input = "DATE_DIFF ( 12-04-2020 , 12-04-2020 )";
        String expected = "12-04-2020 12-04-2020 DATE_DIFF";

        assertEquals(expected, getTokenAsString(input));
    }
}

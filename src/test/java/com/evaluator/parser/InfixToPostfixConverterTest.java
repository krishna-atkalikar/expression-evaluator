package com.evaluator.parser;

import org.junit.Test;

import static com.evaluator.parser.InfixToPostfixConverter.convert;
import static org.junit.Assert.assertEquals;

/**
 * @author shrikrushna on 2020-04-20
 */
public class InfixToPostfixConverterTest {

    public static void main(String[] args) {


        try {
            String s = convert("1 + 3 * 3 + pow ( 2 , 3 )");
            System.out.println("s = " + s);
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
//            Expression make = ExpressionMaker.make(tokens);
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
        assertEquals("1 2 +", convert("1 + 2"));
    }

    @Test
    public void simpleExpressionWithTwoOperator() {
        assertEquals("1 2 3 * +", convert("1 + 2 * 3"));
    }

    @Test
    public void simpleExpressionWithTwoOperatorAndParenthesis() {
        assertEquals("1 2 + 3 *", convert("( 1 + 2 ) * 3"));
    }

    @Test
    public void expressionWithFunction() {
        assertEquals("1 3 3 * + 2 3 pow +", convert("1 + 3 * 3 + pow ( 2 , 3 )"));
    }

    @Test
    public void complexExpressionWithFunction() {
        assertEquals("250000.0 Salary 250000.0 - MIN 5.0 * 100.0 /", convert("MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0"));
    }

    @Test
    public void complexExpressionWithTernaryExpression() {
        String input = "IF ( ( Salary <= 250000.0 ) , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )";
        String expected = "Salary 250000.0 <= 0.0 250000.0 Salary 250000.0 - MIN 5.0 * 100.0 / IF";

        assertEquals(expected, convert(input));
    }

    @Test
    public void testSlab2Expression() {
        String input = "IF ( ( Salary > 500000 ) , ( MIN ( 500000 , ( Salary - 500000 ) ) * 20 / 100 ) , 0 )";
        String expected = "Salary 500000 > 500000 Salary 500000 - MIN 20 * 100 / 0 IF";

        assertEquals(expected, convert(input));
    }

    @Test
    public void slab3ExpressionTest() {
        String input = "IF ( ( Salary > 1000000 ) , ( ( Salary - 1000000 ) * 30 / 100 ) , 0 )";
        String expected = "Salary 1000000 > Salary 1000000 - 30 * 100 / 0 IF";

        assertEquals(expected, convert(input));
    }

    @Test
    public void arithmeticWithLogcalExpression() {
        String input = "Salary < 10 + 5";
        String expected = "Salary 10 5 + <";

        assertEquals(expected, convert(input));
    }
}

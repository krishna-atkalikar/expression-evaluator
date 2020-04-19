package com.evaluator.parser;

import com.evaluator.expression.Expression;

import java.util.LinkedList;

import static com.evaluator.parser.TokenType.*;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionParser {

    private static Tokenizer tokenizer = new Tokenizer();

    static {
        tokenizer.add("POW|IF|MIN", 1, FUNCTION);
        tokenizer.add("\\+|-", 4, OPERATOR);
        tokenizer.add("\\*|/", 5, OPERATOR);
        tokenizer.add("\\<=", 5, OPERATOR);
        tokenizer.add("0?-?\\+?\\d+(\\.\\d+)?", 6, CONSTANT);
        tokenizer.add("\"[^\"]*\"|((?=_[a-z_A-Z_0-9]|[A-Z_a-z])[A-Z_a-z_0-9]+)", 7, VARIABLE);
    }

    public static Expression parse(String expr) {
        String postFixExpr = InfixToPostfixConverter.convert(expr);
        LinkedList<Token> tokens = tokenizer.tokenize(postFixExpr);
        return ExpressionMaker.make(tokens);
    }

//    public static void main(String[] args) {


//
//        try {
//            LinkedList<Token> tokens = tokenizer.tokenize(" (1 - var_12) * sin(x) ");
//            LinkedList<Token> tokens = tokenizer.tokenize(" 1 + pow(1, 2)");
//            LinkedList<Token> tokens = tokenizer.tokenize(" IF(Salary <= 250000, 0, MIN(250000, (Salary - 250000)) * 5 / 100)");
//            LinkedList<Token> tokens = tokenizer.tokenize(" 1 var 3 * + var 3 pow +");
//            String s = infixToPostfix("1 + 3 * 3 + pow ( 2 , 3 )");
//            String s = infixToPostfix("( 2 + 2 ) * 2");
//            String s = infixToPostfix("IF ( Salary <= 250000.0 , 0.0 , MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0 )");
    //Salary  250000.0 <= 0.0 250000 salary 250000.0 - MIN 5.0 * 100.0 / IF
//            String s = infixToPostfix("MIN ( 250000.0 , ( Salary - 250000.0 ) ) * 5.0 / 100.0");
//            String s1 = "250000 salary 250000.0 - MIN 5.0 * 100.0 /";
    //250000 salary 250000.0 - MIN 5.0 * 100.0 /

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
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
//
//    }
}

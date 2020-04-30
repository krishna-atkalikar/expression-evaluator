package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.expression.Expression;
import com.evaluator.operator.Operators;
import com.evaluator.parser.token.Token;

import java.util.List;
import java.util.Stack;

/**
 * Expression builder which takes tokens as input and output a well formed expression that can be evaluated.
 *
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionBuilder {


    /**
     * Creates an expression from string.
     *
     * @param expr
     * @return
     */
    public static Expression build(String expr) {
        List<Token> tokens = ExpressionParser.parse(expr);
        return ExpressionBuilder.build(tokens);
    }

    /**
     * Builds expression from given tokens. To get well a formed expression tokens needs to be postfix order.
     *
     * @param tokens list of tokens
     * @return a well formed expression that is ready for evaluation.
     */
    public static Expression build(List<Token> tokens) {
        return new ExpressionMaker(tokens).invoke(tokens);
    }

    private static class ExpressionMaker {

        private final TokenHandler tokenHandler;
        private Stack<Expression> expressions;

        public ExpressionMaker(List<Token> tokens) {
            expressions = new Stack<>();
            tokenHandler = new GenericTokenHandler(
                    new UnaryOperatorTokenHandler(
                            new BinaryOperatorTokenHandler(
                                    new TernaryOperatorTokenHandler())));
        }

        public Expression invoke(List<Token> tokens) {
            tokens.forEach(tokenHandler::handle);
            throwIfExpressionSizeIsNotOne();
            return expressions.pop();
        }

        private void throwIfExpressionSizeIsNotOne() {
            if (expressions.size() != 1) {
                throw new InvalidExpressionException("Invalid expression." + expressions);
            }
        }

        private interface TokenHandler {

            void handle(Token token);
        }

        private abstract class AbstractHandler implements TokenHandler {

            protected TokenHandler next;

            public AbstractHandler(TokenHandler next) {
                this.next = next;
            }

            protected TokenHandler setNext(TokenHandler next) {
                this.next = next;
                return next;
            }
        }

        private class GenericTokenHandler extends AbstractHandler {

            public GenericTokenHandler(TokenHandler next) {
                super(next);
            }

            @Override
            public void handle(Token token) {
                if (!token.isOperatorToken()) {
                    expressions.push(token.toExpression());
                } else {
                    next.handle(token);
                }
            }
        }

        private class UnaryOperatorTokenHandler extends AbstractHandler {

            public UnaryOperatorTokenHandler(TokenHandler next) {
                super(next);
            }

            @Override
            public void handle(Token token) {
                if (Operators.isUnary(token.getToken())) {
                    expressions.push(token.toExpression(expressions.pop()));
                } else {
                    next.handle(token);
                }
            }
        }

        private class BinaryOperatorTokenHandler extends AbstractHandler {

            public BinaryOperatorTokenHandler(TokenHandler next) {
                super(next);
            }

            @Override
            public void handle(Token token) {
                if (Operators.isBinary(token.getToken())) {
                    Expression right = expressions.pop();
                    Expression left = expressions.pop();
                    expressions.push(token.toExpression(left, right));
                } else {
                    next.handle(token);
                }
            }
        }

        private class TernaryOperatorTokenHandler implements TokenHandler {

            @Override
            public void handle(Token token) {
                Expression right = expressions.pop();
                Expression left = expressions.pop();
                expressions.push(token.toExpression(expressions.pop(), left, right));
            }
        }
    }
}
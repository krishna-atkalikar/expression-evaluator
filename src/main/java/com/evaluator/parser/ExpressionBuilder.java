package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.expression.Expression;
import com.evaluator.operator.Operators;
import com.evaluator.parser.token.Token;

import java.util.EmptyStackException;
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
		return new ExpressionMaker().makeExpression(tokens);
	}

	private static class ExpressionMaker {

		private final TokenHandler tokenHandler;
		private Stack<Expression> expressions;

		public ExpressionMaker() {
			expressions = new Stack<>();
			tokenHandler = new GenericTokenHandler();
			tokenHandler.setNext(new UnaryOperatorTokenHandler())
					.setNext(new BinaryOperatorTokenHandler())
					.setNext(new TernaryOperatorTokenHandler());
		}

		public Expression makeExpression(List<Token> tokens) {
			tokens.forEach(tokenHandler::handle);
			throwIfExpressionIsNotCorrectlyFormed();
			return expressions.pop();
		}

		private void throwIfExpressionIsNotCorrectlyFormed() {
			if (expressions.size() != 1) {
				throw new InvalidExpressionException("Invalid expression." + expressions);
			}
		}

		/**
		 * Handles token and based on type of token converts it to expression.
		 */
		private interface TokenHandler {

			void handle(Token token);

			TokenHandler setNext(TokenHandler next);
		}

		private abstract class AbstractHandler implements TokenHandler {

			protected TokenHandler next;

			public TokenHandler setNext(TokenHandler next) {
				this.next = next;
				return next;
			}
		}

		private class GenericTokenHandler extends AbstractHandler {

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

			@Override
			public void handle(Token token) {
				if (Operators.isUnary(token.getToken())) {
					try {
						expressions.push(token.toExpression(expressions.pop()));
					} catch (EmptyStackException ex) {
						throw new IncorrectNumberOfArgumentException(token.getToken());
					}
				} else {
					next.handle(token);
				}
			}
		}

		private class BinaryOperatorTokenHandler extends AbstractHandler {

			@Override
			public void handle(Token token) {
				if (Operators.isBinary(token.getToken())) {
					try {
						Expression right = expressions.pop();
						Expression left = expressions.pop();
						expressions.push(token.toExpression(left, right));
					} catch (EmptyStackException ex) {
						throw new IncorrectNumberOfArgumentException(token.getToken());
					}
				} else {
					next.handle(token);
				}
			}
		}

		private class TernaryOperatorTokenHandler extends AbstractHandler {

			@Override
			public void handle(Token token) {
				try {
					Expression right = expressions.pop();
					Expression left = expressions.pop();
					expressions.push(token.toExpression(expressions.pop(), left, right));
				} catch (EmptyStackException ex) {
					throw new IncorrectNumberOfArgumentException(token.getToken());
				}
			}
		}
	}
}
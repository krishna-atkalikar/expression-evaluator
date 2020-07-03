package com.evaluator.parser;

import com.evaluator.InvalidExpressionException;
import com.evaluator.operator.Operators;
import com.evaluator.parser.token.*;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * This is shunting yard algorithm implementation for converting infix expression to postfix expression.
 *
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionParser {

    private static final String PAREN_LEFT = "[(]";
    private static final String PAREN_RIGHT = "[)]";
    private static final String VAR_NAME = "(\\$.*?\\$)";
    private static final String NUMBER = "0?-?\\+?\\d+(\\.\\d+)?";
    private static final String DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private static final String SPACE = "[\\s]";

    private static final Set<String> ignorableTokens = ImmutableSet.of(",");

    /**
     * Parses input string expression and returns tokens to be used for building expression.
     *
     * @param expr input infix expression
     * @return Tokens in postfix form.
     */
    public static List<Token> parse(String expr) {
        return new Parser().parse(expr);
    }

	//1 + 2 => 1 2 +, 1 + 2 * 3 => 1 + 2 3 * = > 1 2 3 * +
	private static class Parser {

		private final Stack<String> operators;
		private final List<Token> tokensToReturn;
		private final TokenProcessor tokenProcessor;

		public Parser() {
			operators = new Stack<>();
			tokensToReturn = new LinkedList<>();
			tokenProcessor = new IgnorableTokenProcessor();
			tokenProcessor.setNext(new NumberTokenProcessor())
					.setNext(new OperatorTokenProcessor())
					.setNext(new LeftParenthesisTokenProcessor())
					.setNext(new RightParenthesisTokenProcessor())
					.setNext(new DateTokenProcessor())
					.setNext(new VariableTokenProcessor())
					.setNext(new ConstantTokenProcessor());
		}

		private static boolean isNumber(String str) {
			return str.matches(NUMBER);
		}

		private static boolean isOperator(String symbol) {
			return Operators.isOperator(symbol);
		}

		private static int operatorPrecedence(String symbol) {
			return Operators.getPrecedence(symbol);
		}

		public List<Token> parse(String expr) {
			String[] tokens = expr.trim().split(SPACE);
			Arrays.stream(tokens).forEach(tokenProcessor::handle);
			processRemainingOperators();
			return tokensToReturn;
		}

		private void processRemainingOperators() {
			while (!operators.empty()) {
				if (operators.peek().matches(PAREN_LEFT) || operators.peek().matches(PAREN_RIGHT)) {
					throw new InvalidExpressionException("Mismatched Parentheses.");
				}
				tokensToReturn.add(new OperatorToken(operators.pop()));
			}
		}

		private boolean canPopOperator(String token) {
			return isOperator(operators.peek()) && (operatorPrecedence(token) <= operatorPrecedence(operators.peek()));
		}

		private interface TokenProcessor {

			void handle(String token);

			TokenProcessor setNext(TokenProcessor next);
		}

		private abstract class AbstractTokenProcessor implements TokenProcessor {

			protected TokenProcessor next;

			public TokenProcessor setNext(TokenProcessor next) {
				this.next = next;
				return next;
			}
		}

		private class IgnorableTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (!ignorableTokens.contains(token)) {
					next.handle(token);
				}
			}
		}

		private class NumberTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (isNumber(token)) {
					tokensToReturn.add(new ConstantToken(token));
				} else {
					next.handle(token);
				}
			}
		}

		private class OperatorTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (isOperator(token)) {
					while (!operators.empty() && canPopOperator(token)) {
						tokensToReturn.add(new OperatorToken(operators.pop()));
					}
					operators.push(token);
				} else {
					next.handle(token);
				}
			}
		}

		private class LeftParenthesisTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (token.matches(PAREN_LEFT)) {
					operators.push(token);
				} else {
					next.handle(token);
				}
			}
		}

		private class RightParenthesisTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (!operators.empty() && token.matches(PAREN_RIGHT)) {
					while (!operators.empty() && !operators.peek().matches(PAREN_LEFT)) {
						tokensToReturn.add(new OperatorToken(operators.pop()));
					}
					if (operators.peek().equals("(")) {
						operators.pop();
					} else {
						throw new InvalidExpressionException("Mismatched Parentheses.");
					}
				} else {
					next.handle(token);
				}
			}
		}

		private class DateTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (token.matches(DATE)) {
					tokensToReturn.add(new DateToken(token));
				} else {
					next.handle(token);
				}
			}
		}

		private class VariableTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				if (token.matches(VAR_NAME)) {
					tokensToReturn.add(new VariableToken(StringUtils.removeEnd(token, "$").replaceFirst("\\$", "")));
				} else {
					next.handle(token);
				}
			}
		}

		private class ConstantTokenProcessor extends AbstractTokenProcessor {

			@Override
			public void handle(String token) {
				tokensToReturn.add(new ConstantToken(token));
			}
		}
	}
}

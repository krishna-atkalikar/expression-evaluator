package com.evaluator.parser;

import com.evaluator.expression.Expression;
import com.evaluator.factory.Expressions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.evaluator.factory.Expressions.constant;
import static com.evaluator.factory.Expressions.param;
import static com.evaluator.operator.OperationType.*;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionMaker {

    private static Map<String, BiFunction<Expression, Expression, Expression>> binaryOperators = new HashMap<>();
    private static Map<String, Function<Expression, Expression>> unaryOperators = new HashMap<>();

    static {
        binaryOperators.put(ADD.getSymbol(), Expressions::add);
        binaryOperators.put(SUBTRACT.getSymbol(), Expressions::sub);
        binaryOperators.put(MULTIPLICATION.getSymbol(), Expressions::multiply);
        binaryOperators.put(DIVISION.getSymbol(), Expressions::divide);
        binaryOperators.put(LT.getSymbol(), Expressions::lt);
        binaryOperators.put(GT.getSymbol(), Expressions::gt);
        binaryOperators.put(LTE.getSymbol(), Expressions::lte);
        binaryOperators.put(GTE.getSymbol(), Expressions::gte);
        binaryOperators.put(EQ.getSymbol(), Expressions::eq);
        binaryOperators.put(NEQ.getSymbol(), Expressions::nEq);
        binaryOperators.put(AND.getSymbol(), Expressions::and);
        binaryOperators.put(OR.getSymbol(), Expressions::or);

        binaryOperators.put(POWER.getSymbol(), Expressions::pow);
        binaryOperators.put(MIN.getSymbol(), Expressions::min);

        unaryOperators.put(NOT.getSymbol(), Expressions::not);
        unaryOperators.put(CEILING.getSymbol(), Expressions::ceil);
        unaryOperators.put(FLOOR.getSymbol(), Expressions::floor);


    }

    public static Expression make(List<Token> list) {
        Stack<Expression> expressions = new Stack<>();

        for (Token token : list) {
            if (token.isVariableToken()) {
                expressions.push(param(token.getToken()));
            } else if (token.isConstantToken()) {
                expressions.push(constant(token.getToken()));
            } else if (token.isOperatorToken()) {
                Expression right = expressions.pop();
                Expression left = expressions.pop();
                if (binaryOperators.containsKey(token.getToken())) {
                    expressions.push(binaryOperators.get(token.getToken()).apply(left, right));
                } else if (token.getToken().equalsIgnoreCase("IF")) {
                    expressions.push(Expressions.iff(expressions.pop(), left, right));
                }
            }
        }
        return expressions.pop();
    }

}

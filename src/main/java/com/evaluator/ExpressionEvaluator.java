package com.evaluator;

import com.evaluator.expression.Expression;
import com.evaluator.parser.ExpressionBuilder;
import com.evaluator.parser.ExpressionParser;
import com.evaluator.parser.token.Token;
import com.evaluator.visitor.ExpressionVisitor;
import com.evaluator.visitor.Visitor;

import java.util.List;
import java.util.Map;

/**
 * Evaluates expression and returns result.
 *
 * @author shrikrushna on 2020-04-19
 */
public class ExpressionEvaluator<T> {


    /**
     * Evaluates an expression without dataset.
     *
     * @param expr
     * @return
     */
    public T evaluate(String expr) {
        Expression expression = getExpression(expr);
        Visitor<T> visitor = new ExpressionVisitor<>();
        return visitor.visit(expression);
    }

    /**
     * Accepts an expression and data set to be evaluated. and returns the result of expression.
     *
     * @param expr expression to be evaluated.
     * @param dataSet    parameter name to it value map.
     * @return result of expression evaluation.
     */
    public T evaluate(String expr, Map<String, Expression> dataSet) {
        Expression expression = getExpression(expr);
        Visitor<T> visitor = new ExpressionVisitor<>(dataSet);
        return visitor.visit(expression);
    }

    /**
     * Creates an expression from string.
     *
     * @param expr
     * @return
     */
    public Expression getExpression(String expr) {
        List<Token> tokens = ExpressionParser.parse(expr);
        return ExpressionBuilder.build(tokens);
    }
}

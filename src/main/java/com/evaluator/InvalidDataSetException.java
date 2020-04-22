package com.evaluator;

import com.evaluator.expression.ParameterExpression;

/**
 * @author shrikrushna on 2020-04-22
 */
public class InvalidDataSetException extends RuntimeException {

    private ParameterExpression parameterExpression;

    public InvalidDataSetException(ParameterExpression parameterExpression) {
        super("Value for parameter " + parameterExpression.getParamName() + " is not present in the data-set.");
        this.parameterExpression = parameterExpression;
    }

    public ParameterExpression getParameterExpression() {
        return parameterExpression;
    }
}

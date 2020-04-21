package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-19
 */
public class ParameterExpression implements Expression {

    private String paramName;

    public ParameterExpression(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override public String toString() {
        return "ParameterExpression{" +
                "paramName='" + paramName + '\'' +
                '}';
    }
}

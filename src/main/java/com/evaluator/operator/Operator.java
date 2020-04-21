package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-21
 */
public interface Operator {

    String getSymbol();

    int getPrecedence();

    boolean isLeftAssociative();
}

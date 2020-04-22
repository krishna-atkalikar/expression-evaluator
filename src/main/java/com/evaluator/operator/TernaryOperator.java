package com.evaluator.operator;

/**
 * An enum where all the ternary operators are present along with their attributes and necessary details.
 *
 * @author shrikrushna on 2020-04-22
 */
public enum TernaryOperator implements Operator {

    IF("IF", 4, true);

    private final String symbol;
    private final int precedence;
    private final boolean isLeftAssociative;

    TernaryOperator(String symbol, int precedence, boolean isLeftAssociative) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public boolean isLeftAssociative() {
        return isLeftAssociative;
    }
}

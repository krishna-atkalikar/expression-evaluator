package com.evaluator.operator;

/**
 * @author shrikrushna on 2020-04-18
 */
public enum OperationType {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),

    LT("<"),
    LTE("<="),
    GT(">"),
    GTE(">="),
    EQ("=="),
    NEQ("!="),

    AND("&&"),
    OR("||"),
    NOT("!"),

    IF("IF"),
    POWER("POW"),
    MIN("MIN"),
    FLOOR("FLOOR"),
    CEILING("CEIL"),

    DATE_DIFFERENCE("DATE_DIFF"),
    ADD_DAYS_TO_DATE("ADD_DAYS_TO_DATE"),
    SUBTRACT_DAYS_FROM_DATE("SUBTRACT_DAYS_FROM_DATE"),

    ;

    private String symbol;

    OperationType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

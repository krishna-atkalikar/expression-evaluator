package com.evaluator.operator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shrikrushna on 2020-04-18
 */
public enum OperationType {

    ADD("+", 2, true),
    SUBTRACT("-", 2, true),
    MULTIPLICATION("*", 3, true),
    DIVISION("/", 3, true),

    LT("<", 1, true),
    LTE("<=", 1, true),
    GT(">", 1, true),
    GTE(">=", 1, true),
    EQ("==", 1, true),
    NEQ("!=", 1, true),

    AND("&&", 1, true),
    OR("||", 1, true),
    NOT("!", 4, true),

    IF("IF", 4, true),
    POWER("POW", 4, true),
    MIN("MIN", 4, true),
    FLOOR("FLOOR", 4, true),
    CEILING("CEIL", 4, true),

    DATE_DIFFERENCE("DATE_DIFF", 4, true),
    ADD_DAYS_TO_DATE("ADD_DAYS_TO_DATE", 4, true),
    SUBTRACT_DAYS_FROM_DATE("SUBTRACT_DAYS_FROM_DATE", 4, true),
    ;

    private String symbol;
    private static final Map<String, OperationType> map = new HashMap<>();

    static {
        for (OperationType value : values()) {
            map.put(value.getSymbol(), value);
        }
    }

    private final int precedence;
    private final boolean isLeftAssociative;

    OperationType(String symbol, int precedence, boolean isLeftAssociative) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
    }

    public String getSymbol() {
        return symbol;
    }

    public static OperationType from(String symbol) {
        return map.get(symbol);
    }

    public static boolean isOperator(String symbol) {
        return map.containsKey(symbol);
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isLeftAssociative() {
        return isLeftAssociative;
    }
}

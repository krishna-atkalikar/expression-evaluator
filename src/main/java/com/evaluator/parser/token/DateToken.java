package com.evaluator.parser.token;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date token
 *
 * @author shrikrushna on 2020-04-21
 */
public class DateToken extends Token {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate date;

    public DateToken(String token) {
        super(token);
        date = LocalDate.parse(token, formatter);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean isDateToken() {
        return true;
    }
}

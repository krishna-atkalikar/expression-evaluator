package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * Represent an expression.
 *
 * @author shrikrushna on 2020-04-11
 */
public interface Expression {

    <T> T accept(Visitor<T> visitor);
}

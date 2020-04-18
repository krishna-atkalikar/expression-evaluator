package com.evaluator.expression;

import com.evaluator.visitor.Visitor;

/**
 * @author shrikrushna on 2020-04-11
 */
public abstract class Expression {

    public abstract <T> T accept(Visitor<T> visitor);
}

package org.evolboot.core.data.jpa.querydsl;

import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;

/**
 * @author evol
 */
public class BitwiseExpressions {

    public static NumberTemplate<Integer> bitand(Object arg1, Object arg2) {
        return Expressions.numberTemplate(Integer.class, "function('bitand', {0}, {1})", arg1, arg2);
    }

    public static NumberTemplate<Integer> bitor(Object arg1, Object arg2) {
        return Expressions.numberTemplate(Integer.class, "function('bitor', {0}, {1})", arg1, arg2);
    }

    public static NumberTemplate<Integer> bitxor(Object arg1, Object arg2) {
        return Expressions.numberTemplate(Integer.class, "function('bitxor', {0}, {1})", arg1, arg2);
    }
}

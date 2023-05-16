package org.evolboot.core.data.jpa.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

/**
 * Json 搜索器
 *
 * @author evol
 */
public class JsonSearchExpressions {

    private final static String jsonContainsExpression = "json_contains({0}, {1}) = true";

    public static BooleanExpression jsonContains(Object arg1, String arg2) {
        return Expressions.booleanTemplate(jsonContainsExpression, arg1, formatParam(arg2));
    }

    public static BooleanExpression jsonContainsAnd(Object arg1, String... arg) {
        BooleanExpression booleanExpression = jsonContains(arg1, arg[0]);
        for (int i = 1; i < arg.length; i++) {
            booleanExpression = booleanExpression.and(jsonContainsAnd(arg1, arg[i]));
        }
        return booleanExpression;
    }

    public static BooleanExpression jsonContainsOr(Object arg1, String... arg) {
        BooleanExpression booleanExpression = jsonContains(arg1, arg[0]);
        for (int i = 1; i < arg.length; i++) {
            booleanExpression = booleanExpression.or(jsonContainsAnd(arg1, arg[i]));
        }
        return booleanExpression;
    }

    private static String formatParam(String arg) {
        return "\"" + arg + "\"";
    }

}

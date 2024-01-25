package org.evolboot.core.data.jpa.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

/**
 * Json 搜索器
 *
 * @author evol
 */
public class JsonSearchExpressions {

    private final static String JSON_SEARCH = "json_search({0}, {1}, {2}) is not null";

    public static BooleanExpression jsonSearch(Object field, String arg2) {
        return Expressions.booleanTemplate(JSON_SEARCH, field, "all", "%" + arg2 + "%");
    }


}

package org.evolboot.core.data.jpa.dialect;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;

/**
 * @author evol
 */
public class CustomMysql5Dialect extends MySQL5Dialect {

    public CustomMysql5Dialect() {
        super();
        registerFunction("bitand", new SQLFunctionTemplate(IntegerType.INSTANCE, "(?1 & ?2)"));
        registerFunction("bitor", new SQLFunctionTemplate(IntegerType.INSTANCE, "(?1 | ?2)"));
        registerFunction("bitxor", new SQLFunctionTemplate(IntegerType.INSTANCE, "(?1 ^ ?2)"));
    }
}

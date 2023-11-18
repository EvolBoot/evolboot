package org.evolboot.core.entity;

import cn.hutool.core.util.IdUtil;
import org.evolboot.core.lang.Snowflake;

/**
 * @author evol
 */
public abstract class IdGenerate {

    private static Snowflake snowflake = new Snowflake(0L);

    public static void setSnowflake(Snowflake snowflake) {
        IdGenerate.snowflake = snowflake;
    }

    public static String stringId() {
        return IdUtil.fastSimpleUUID();
    }

    public static Long longId() {
        return snowflake.nextId();
    }
}

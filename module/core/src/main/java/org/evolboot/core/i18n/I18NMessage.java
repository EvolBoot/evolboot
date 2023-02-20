package org.evolboot.core.i18n;

import java.util.List;

/**
 * 如果使用数据库存储国际化消息，则实现本消息，然后配置 I18NConfig 中注入此实现
 *
 * @author evol
 */
public interface I18NMessage {

    default String message(String code) {
        return code;
    }

    default String message(String code, Object... args) {
        return code;
    }

    default String message(String code, List<?> args) {
        return code;
    }

}

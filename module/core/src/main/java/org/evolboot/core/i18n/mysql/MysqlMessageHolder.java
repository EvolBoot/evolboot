package org.evolboot.core.i18n.mysql;

import org.evolboot.core.i18n.DelegatingLocaleContextHolder;
import org.evolboot.core.i18n.I18NMessage;

import java.util.Locale;

/**
 * 假设使用Mysql，可以另外新建一个模块，然后单独配置，调用I18NConfig ，将此注入进去
 * @author evol
 */
public class MysqlMessageHolder implements I18NMessage {

    @Override
    public String message(String code) {
         // 获取当前请求中的本地化语言
        Locale locale = DelegatingLocaleContextHolder.getLocale();
        // 然后去数据库当中匹配 code + locale的多语言消息，再返回
        return "消息多语言";
    }
}

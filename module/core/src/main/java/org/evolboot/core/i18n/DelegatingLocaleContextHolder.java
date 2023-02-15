package org.evolboot.core.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;

import java.util.Locale;

/**
 * 切换语言环境工具类
 *
 * @author evol
 */
public abstract class DelegatingLocaleContextHolder {

    public static String getLocalLanguage() {
        return LocaleContextHolder.getLocale().toLanguageTag();
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * 修改当前语言
     *
     * @param locale
     */
    public static void setLocalLanguage(Locale locale) {
        LocaleContextHolder.setLocaleContext(new SimpleLocaleContext(locale));
    }

    /**
     * 设置默认语言为英语
     */
    public static void setLocalEnglish() {
        LocaleContextHolder.setLocaleContext(new SimpleLocaleContext(Locale.ENGLISH));
    }
}

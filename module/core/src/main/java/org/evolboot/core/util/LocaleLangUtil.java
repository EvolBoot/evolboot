package org.evolboot.core.util;

import cn.hutool.core.util.ReflectUtil;
import org.evolboot.core.DefaultConfig;
import org.evolboot.core.lang.LocaleLanguage;

import java.util.Collection;

/**
 * @author evol
 * 
 */
public final class LocaleLangUtil {

    public static <T extends LocaleLanguage> T getByLanguage(Collection<T> locales, String language, Class<T> clazz) {
        T locale = locales
                .stream()
                .filter(_locale ->
                        _locale.getLanguage().equalsIgnoreCase(language)
                )
                .findFirst().orElse(null);
        if (ExtendObjects.isNull(locale)) {
            locale = locales
                    .stream()
                    .filter(_locale ->
                            _locale.getLanguage().equalsIgnoreCase(DefaultConfig.getDefaultLanguageTag())
                    )
                    .findFirst().orElse(null);
        }
        if (ExtendObjects.isNull(locale)) {
            locale = locales
                    .stream()
                    .findFirst().orElse(null);
        }
        if (ExtendObjects.isNull(locale)) {
            locale = ReflectUtil.newInstance(clazz);
        }
        return locale;

    }


}

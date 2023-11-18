package org.evolboot.core.entity;


import org.evolboot.core.i18n.DelegatingLocaleContextHolder;
import org.evolboot.core.lang.LocaleLanguage;
import org.evolboot.core.util.LocaleLangUtil;

import java.util.Collection;

/**
 * @author evol
 */
public interface LocaleDomainPart<T extends LocaleLanguage> {

    Collection<T> getLocales();

    default T findLocaleByLanguage(String language, Class<T> clazz) {
        return LocaleLangUtil.getByLanguage(getLocales(), language, clazz);
    }

    default T findLocaleByCurrentLanguage(Class<T> clazz) {
        String language = DelegatingLocaleContextHolder.getLocalLanguage();
        return findLocaleByLanguage(language, clazz);
    }


}

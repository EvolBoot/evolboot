package org.evolboot.locale;

import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class LocaleI18nMessage {

    public static final String NAMESPACE = "locale";


    public static class Language {
        public static final String NAMESPACE = LocaleI18nMessage.NAMESPACE + ".language";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }

    public static class Region {
        public static final String NAMESPACE = LocaleI18nMessage.NAMESPACE + ".region";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }


}

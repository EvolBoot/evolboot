package org.evolboot.common;


import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class CommonI18nMessage {

    public static final String NAMESPACE = "common";

    public static final String NOT_FOUND = NAMESPACE + ".notFound";


    public static class Config {

        public static final String NAMESPACE = CommonI18nMessage.NAMESPACE + "config";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }

    }

    /**
     * 字典key
     */
    public static class DictKey {
        public static final String NAMESPACE = CommonI18nMessage.NAMESPACE + ".dictkey";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 字典Value
     */
    public static class DictValue {
        public static final String NAMESPACE = CommonI18nMessage.NAMESPACE + ".dictvalue";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }
}

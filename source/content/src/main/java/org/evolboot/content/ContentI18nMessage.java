package org.evolboot.content;

import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 * 
 */
public abstract class ContentI18nMessage {

    public static final String NAMESPACE = "content";


    public static class News {
        public static final String NAMESPACE = ContentI18nMessage.NAMESPACE + ".news";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }

    /**
     * banner
     */
    public static class Banner {
        public static final String NAMESPACE = ContentI18nMessage.NAMESPACE + ".banner";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }


    /**
     * 启动页
     */
    public static class StartupPage {
        public static final String NAMESPACE = ContentI18nMessage.NAMESPACE + ".startuppage";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }


    /**
     * 公告
     */
    public static class Notice {
        public static final String NAMESPACE = ContentI18nMessage.NAMESPACE + ".notice";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }


    /**
     * QA
     */
    public static class Qa {
        public static final String NAMESPACE = ContentI18nMessage.NAMESPACE + ".qa";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
        }
    }


}

package org.evolboot.system;

import org.evolboot.core.i18n.I18NMessageHolder;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 系统模块
 *
 * @author evol
 */
public abstract class SystemI18nMessage {

    public static final String NAMESPACE = "system";


    public static class UserLoginLog {
        public static final String NAMESPACE = SystemI18nMessage.NAMESPACE + ".userloginlog";

        String PAGE = "user_login_log_page", HAS_PAGE = AUTHORITY_PREFIX + PAGE + AUTHORITY_SUFFIX;


        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }

    }


    /**
     * 字典key
     */
    public static class DictKey {
        public static final String NAMESPACE = SystemI18nMessage.NAMESPACE + ".dictkey";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


    /**
     * 字典Value
     */
    public static class DictValue {
        public static final String NAMESPACE = SystemI18nMessage.NAMESPACE + ".dictvalue";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }


}

package org.evolboot.system;

import org.evolboot.core.i18n.I18NMessageHolder;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 系统模块
 *
 * @author evol
 * 
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
     * APP更新
     */
    public static class AppUpgrade {
        public static final String NAMESPACE = SystemI18nMessage.NAMESPACE + ".appupgrade";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }


}

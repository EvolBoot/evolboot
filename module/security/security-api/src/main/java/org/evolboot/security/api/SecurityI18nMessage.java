package org.evolboot.security.api;


import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class SecurityI18nMessage {

    public static final String NAMESPACE = "security.api";

    public static final String ACCESS_TOKEN_EXPIRED = NAMESPACE + ".accessTokenExpiredException";
    public static final String accessIsDenied = NAMESPACE + ".accessIsDenied";

    public static String accessTokenExpired() {
        return I18NMessageHolder.message(ACCESS_TOKEN_EXPIRED);
    }


}

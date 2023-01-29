package org.evolboot.security.api;


import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class SecurityI18nMessage {

    public static final String NAMESPACE = "security";

    public static final String ACCESS_TOKEN_EXPIRED = NAMESPACE + ".accessTokenExpiredException";

    public static String accessTokenExpired() {
        return MessageHolder.message(ACCESS_TOKEN_EXPIRED, "");
    }


}

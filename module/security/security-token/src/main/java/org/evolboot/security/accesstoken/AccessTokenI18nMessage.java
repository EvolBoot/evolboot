package org.evolboot.security.accesstoken;

import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class AccessTokenI18nMessage {

    public static final String NAMESPACE = "security.accesstoken";


     public static final String AUTHENTICATION_ERROR = NAMESPACE + ".authenticationError";




    public static String authenticationError() {
        return I18NMessageHolder.message(AUTHENTICATION_ERROR, "");
    }

}

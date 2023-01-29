package org.evolboot.security.accesstoken;

import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class AccessTokenI18nMessage {

    public static final String NAMESPACE = "security.accesstoken";


    public static final String ACCESS_TOKEN_EXPIRED = NAMESPACE + ".sessionUserExpired";
    public static final String AUTHENTICATION_ERROR = NAMESPACE + ".authenticationError";


    public static final String SESSION_USER_EXPIRED_EXCEPTION = NAMESPACE + ".sessionUserExpiredException";
    public static final String VERIFY_IMAGE_ERROR = NAMESPACE + ".verifyImageError";
    public static final String USERNAME_NOT_EMPTY = NAMESPACE + ".usernameNotEmpty";
    public static final String PASSWORD_NOT_EMPTY = NAMESPACE + ".passwordNotEmpty";
    public static final String IMG_VERIFY_TOKEN_NOT_EMPTY = NAMESPACE + ".imgVerifyTokenNotEmpty";
    public static final String IMG_VERIFY_CODE_NOT_EMPTY = NAMESPACE + ".imgVerifyCodeNotEmpty";
    public static final String TOKEN_NOT_EXIST = NAMESPACE + ".token.notExist";

    public static String accessTokenExpired() {
        return MessageHolder.message(ACCESS_TOKEN_EXPIRED, "");
    }

    public static String authenticationError() {
        return MessageHolder.message(AUTHENTICATION_ERROR, "");
    }


    public static String imgVerifyTokenNotEmpty() {
        return MessageHolder.message(IMG_VERIFY_TOKEN_NOT_EMPTY, "");
    }

    public static String imgVerifyCodeNotEmpty() {
        return MessageHolder.message(IMG_VERIFY_CODE_NOT_EMPTY, "");
    }


    public static String usernameNotEmpty() {
        return MessageHolder.message(USERNAME_NOT_EMPTY, "");
    }

    public static String passwordNotEmpty() {
        return MessageHolder.message(PASSWORD_NOT_EMPTY, "");
    }


    public static String sessionUserExpiredException() {
        return MessageHolder.message(SESSION_USER_EXPIRED_EXCEPTION, "");
    }

    public static String verifyImageError() {
        return MessageHolder.message(VERIFY_IMAGE_ERROR, "");
    }

    public static String tokenNotExist() {
        return MessageHolder.message(TOKEN_NOT_EXIST, "");
    }
}

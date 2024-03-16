package org.evolboot.security.accesstoken.domain.authentication;

/**
 * @author evol
 */
public enum AuthenticationTokenType {

    /**
     *
     */
    PASSWORD,

    /**
     * 短信验证
     */
    MOBILE_CAPTCHA,

    /**
     * 谷歌验证
     */
    GOOGLE_AUTHENTICATOR;
}

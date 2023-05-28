package org.evolboot.security.accesstoken.domain.authentication;

/**
 * @author evol
 */
public enum AuthenticationTokenType {

    /**
     *
     */
    USERNAME_EMAIL_MOBILE,

    /**
     * 短信验证
     */
    MOBILE_CAPTCHA,

    /**
     * 谷歌验证
     */
    GOOGLE_AUTHENTICATOR,

    /**
     * 图片验证
     */
    IMAGE_CAPTCHA;
}

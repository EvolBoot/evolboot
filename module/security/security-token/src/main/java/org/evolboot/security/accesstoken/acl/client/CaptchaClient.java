package org.evolboot.security.accesstoken.acl.client;

/**
 * @author evol
 */
public interface CaptchaClient {

    void verifyMobileCaptchaIsTrue(String mobilePrefix, String mobile, String mobileCaptchaCode, String mobileCaptchaToken);

    /**
     * 图片验证码
     */
    void verifyImageCaptchaIsTrue(String token, String code);

}

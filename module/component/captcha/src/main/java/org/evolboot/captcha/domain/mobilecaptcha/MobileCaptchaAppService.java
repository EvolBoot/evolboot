package org.evolboot.captcha.domain.mobilecaptcha;

import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaCreateFactory;

/**
 * @author evol
 * 
 */

public interface MobileCaptchaAppService {

    /**
     * 创建短信验证码
     *
     * @return
     */
    MobileCaptcha create(MobileCaptchaCreateFactory.Request request);

    /**
     * 验证验证码
     *
     * @param token
     * @param mobile
     * @param code
     * @return
     */
    boolean verify(String mobilePrefix, String mobile, String token, String code, String internalCode);

    /**
     * 验证码验证码是正确的，错误的直接抛异常
     *
     * @param token
     * @param mobile
     * @param code
     */
    void verifyIsTrue(String mobilePrefix, String mobile, String token, String code, String internalCode);
}

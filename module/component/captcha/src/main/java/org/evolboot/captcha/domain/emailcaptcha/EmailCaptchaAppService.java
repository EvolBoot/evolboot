package org.evolboot.captcha.domain.emailcaptcha;

import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaCreateFactory;

/**
 * @author evol
 */
public interface EmailCaptchaAppService {

    /**
     * 创建邮箱验证码
     *
     * @param request
     * @return
     */
    EmailCaptcha create(EmailCaptchaCreateFactory.Request request);


    /**
     * 校验邮箱验证码
     *
     * @param token        Token
     * @param email        邮箱
     * @param code         验证码
     * @param internalCode 内部码
     */
    boolean verify(String token, String email, String code, String internalCode);

    /**
     * 校验验证码为真
     *
     * @param token        Token
     * @param email        邮箱
     * @param code         验证码
     * @param internalCode 内部码
     */
    void verifyIsTrue(String token, String email, String code, String internalCode);

}

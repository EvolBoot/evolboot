package org.evolboot.identity.acl.client;

import org.evolboot.shared.email.EmailMessageTag;
import org.evolboot.shared.sms.SmsMessageTag;

/**
 * @author evol
 * 
 */
public interface IdentityCaptchaClient {


    /**
     * 验证码短信码
     *
     * @param mobilePrefix       手机号前缀
     * @param mobile             手机号
     * @param mobileCaptchaCode  验证码
     * @param mobileCaptchaToken 验证码Token
     * @param internalCode       内部码
     */
    void verifyMobileCaptchaIsTrue(String mobilePrefix,
                                   String mobile,
                                   String mobileCaptchaCode,
                                   String mobileCaptchaToken,
                                   String internalCode);

    /**
     * 发送短信验证码
     *
     * @param mobilePrefix 手机号前缀
     * @param mobile       手机号
     * @param messageTag   消息类型
     * @param ip           发送IP
     * @param internalCode 内部码
     * @return
     */
    String sendMobileCaptcha(String mobilePrefix,
                             String mobile,
                             SmsMessageTag messageTag,
                             String ip,
                             String internalCode);

    void verifyEmailCaptchaIsTrue(String email,
                                  String emailCaptchaCode,
                                  String emailCaptchaToken,
                                  String internalCode);

    String sendEmailCaptcha(String email,
                            EmailMessageTag messageTag,
                            String ip,
                            String internalCode);
}

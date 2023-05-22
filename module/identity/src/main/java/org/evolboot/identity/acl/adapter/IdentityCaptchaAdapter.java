package org.evolboot.identity.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.EmailCaptchaAppService;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaCreateFactory;
import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaCreateFactory;
import org.evolboot.identity.acl.client.IdentityCaptchaClient;
import org.evolboot.shared.email.EmailMessageTag;
import org.evolboot.shared.sms.SmsMessageTag;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class IdentityCaptchaAdapter implements IdentityCaptchaClient {

    private final MobileCaptchaAppService mobileCaptchaAppService;
    private final EmailCaptchaAppService emailCaptchaAppService;


    public IdentityCaptchaAdapter(MobileCaptchaAppService mobileCaptchaAppService, EmailCaptchaAppService emailCaptchaAppService) {
        this.mobileCaptchaAppService = mobileCaptchaAppService;
        this.emailCaptchaAppService = emailCaptchaAppService;
    }

    @Override
    public void verifyMobileCaptchaIsTrue(String mobilePrefix,
                                          String mobile,
                                          String mobileCaptchaCode,
                                          String mobileCaptchaToken,
                                          String internalCode) {
        mobileCaptchaAppService.verifyIsTrue(mobilePrefix, mobile, mobileCaptchaToken, mobileCaptchaCode, internalCode);
    }

    @Override
    public String sendMobileCaptcha(String mobilePrefix,
                                    String mobile,
                                    SmsMessageTag messageTag,
                                    String ip,
                                    String internalCode) {
        MobileCaptcha mobileCaptcha = mobileCaptchaAppService.create(MobileCaptchaCreateFactory
                .Request
                .builder()
                .mobilePrefix(mobilePrefix)
                .mobile(mobile)
                .messageTag(messageTag)
                .internalCode(internalCode)
                .verifyImageCaptcha(false)
                .ip(ip)
                .build());
        return mobileCaptcha.getToken();
    }

    @Override
    public void verifyEmailCaptchaIsTrue(String email, String emailCaptchaCode, String emailCaptchaToken, String internalCode) {
        emailCaptchaAppService.verifyIsTrue(emailCaptchaToken, email, emailCaptchaCode, internalCode);
    }

    @Override
    public String sendEmailCaptcha(String email, EmailMessageTag messageTag, String ip, String internalCode) {
        EmailCaptcha emailCaptcha = emailCaptchaAppService.create(
                EmailCaptchaCreateFactory
                        .Request
                        .builder()
                        .email(email)
                        .messageTag(messageTag)
                        .ip(ip)
                        .verifyImageCaptcha(false)
                        .internalCode(internalCode)
                        .build()
        );
        return emailCaptcha.getToken();
    }
}

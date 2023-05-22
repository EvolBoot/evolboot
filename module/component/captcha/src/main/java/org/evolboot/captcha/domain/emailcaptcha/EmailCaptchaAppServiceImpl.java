package org.evolboot.captcha.domain.emailcaptcha;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.emailcaptcha.entity.EmailCaptcha;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaCreateFactory;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaVerifyService;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
@Slf4j
public class EmailCaptchaAppServiceImpl implements EmailCaptchaAppService {


    private final EmailCaptchaCreateFactory factory;
    private final EmailCaptchaVerifyService emailCaptchaVerifyService;

    private final ImageCaptchaAppService imageCaptchaAppService;


    public EmailCaptchaAppServiceImpl(EmailCaptchaCreateFactory factory, EmailCaptchaVerifyService emailCaptchaVerifyService, ImageCaptchaAppService imageCaptchaAppService) {
        this.factory = factory;
        this.emailCaptchaVerifyService = emailCaptchaVerifyService;
        this.imageCaptchaAppService = imageCaptchaAppService;
    }

    @Transactional
    @Override
    @NoRepeatSubmit
    public EmailCaptcha create(EmailCaptchaCreateFactory.Request request) {
        return factory.create(request);
    }

    @Transactional
    @Override
    public boolean verify(String token, String email, String code, String internalCode) {
        return emailCaptchaVerifyService.execute(token, email, code, internalCode);
    }

    @Override
    public void verifyIsTrue(String token, String email, String code, String internalCode) {
        boolean verifyIsTrue = emailCaptchaVerifyService.execute(token, email, code, internalCode);
        Assert.isTrue(verifyIsTrue, CaptchaI18nMessage.EmailCaptcha.codeError());
    }

}
package org.evolboot.captcha.domain.mobilecaptcha;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultMobileCaptchaAppService implements MobileCaptchaAppService {

    private final MobileCaptchaCreateFactory factory;

    private final MobileCaptchaRepository repository;

    private final MobileCaptchaVerifyService mobileCaptchaVerifyService;

    private final ImageCaptchaAppService imageCaptchaAppService;

    public DefaultMobileCaptchaAppService(MobileCaptchaCreateFactory factory,
                                          MobileCaptchaRepository repository,
                                          MobileCaptchaVerifyService mobileCaptchaVerifyService, ImageCaptchaAppService imageCaptchaAppService) {
        this.factory = factory;
        this.repository = repository;
        this.mobileCaptchaVerifyService = mobileCaptchaVerifyService;
        this.imageCaptchaAppService = imageCaptchaAppService;
    }

    @Transactional
    @Override
    @NoRepeatSubmit
    public MobileCaptcha create(MobileCaptchaCreateFactory.Request request) {
        return factory.create(request);
    }

    @Transactional
    @Override
    public boolean verify(String mobilePrefix, String mobile, String token, String code, String internalCode) {
        return mobileCaptchaVerifyService.execute(mobilePrefix, mobile, token, code, internalCode);
    }

    @Override
    public void verifyIsTrue(String mobilePrefix, String mobile, String token, String code, String internalCode) {
        boolean verifyResult = mobileCaptchaVerifyService.execute(mobilePrefix, mobile, token, code, internalCode);
        Assert.isTrue(verifyResult, CaptchaI18nMessage.MobileCaptcha.codeError());
    }

}

package org.evolboot.captcha.domain.mobilecaptcha;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;
import org.evolboot.captcha.domain.mobilecaptcha.repository.MobileCaptchaRepository;
import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaCreateFactory;
import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaVerifyService;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.evolboot.captcha.CaptchaI18nMessage.MobileCaptcha.CODE_ERROR;

/**
 * @author evol
 */
@Service
@Slf4j
public class MobileCaptchaAppServiceImpl implements MobileCaptchaAppService {

    private final MobileCaptchaCreateFactory factory;

    private final MobileCaptchaRepository repository;

    private final MobileCaptchaVerifyService mobileCaptchaVerifyService;

    private final ImageCaptchaAppService imageCaptchaAppService;

    public MobileCaptchaAppServiceImpl(MobileCaptchaCreateFactory factory,
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
        Assert.isTrue(verifyResult, I18NMessageHolder.message(CODE_ERROR));
    }

}

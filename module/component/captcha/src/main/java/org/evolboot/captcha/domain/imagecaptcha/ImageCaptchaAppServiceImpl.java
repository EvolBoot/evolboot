package org.evolboot.captcha.domain.imagecaptcha;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.evolboot.captcha.domain.imagecaptcha.service.ImageCaptchaCreateFactory;
import org.evolboot.captcha.domain.imagecaptcha.service.ImageCaptchaVerifyService;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.evolboot.captcha.CaptchaI18nMessage.ImageCaptcha.CODE_ERROR;

/**
 * @author evol
 */
@Service
@Slf4j
public class ImageCaptchaAppServiceImpl implements ImageCaptchaAppService {

    private final ImageCaptchaCreateFactory service;

    private final ImageCaptchaRepository repository;

    private final ImageCaptchaVerifyService imageCaptchaVerifyService;


    public ImageCaptchaAppServiceImpl(ImageCaptchaCreateFactory service, ImageCaptchaRepository repository, ImageCaptchaVerifyService imageCaptchaVerifyService) {
        this.service = service;
        this.repository = repository;
        this.imageCaptchaVerifyService = imageCaptchaVerifyService;
    }

    @Transactional
    @Override
    public ImageCaptchaCreateFactory.Response create(Integer width, Integer height, String ip) {
        return service.create(width, height, ip);
    }

    @Override
    public boolean verify(String token, String codeValue) {
        return imageCaptchaVerifyService.execute(token, codeValue);
    }


    @Override
    public void verifyIsTrue(String token, String codeValue) {
        boolean verifyResult = imageCaptchaVerifyService.execute(token, codeValue);
        Assert.isTrue(verifyResult, I18NMessageHolder.message(CODE_ERROR));
    }

}

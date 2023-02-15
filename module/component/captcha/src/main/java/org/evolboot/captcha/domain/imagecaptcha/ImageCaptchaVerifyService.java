package org.evolboot.captcha.domain.imagecaptcha;

import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.springframework.stereotype.Service;

import static org.evolboot.captcha.CaptchaI18nMessage.ImageCaptcha.CODE_ERROR;

/**
 * @author evol
 * 
 */
@Service
public class ImageCaptchaVerifyService {

    private final ImageCaptchaRepository repository;

    public ImageCaptchaVerifyService(ImageCaptchaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String token, String code) {
        ImageCaptcha imageCaptcha = repository.findByToken(token);
        Assert.notNull(imageCaptcha, I18NMessageHolder.message(CODE_ERROR));
        boolean result = imageCaptcha.verify(code);
        repository.deleteByToken(token);
        return result;
    }
}

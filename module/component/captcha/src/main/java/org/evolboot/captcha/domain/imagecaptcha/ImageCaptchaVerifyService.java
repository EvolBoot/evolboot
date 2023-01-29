package org.evolboot.captcha.domain.imagecaptcha;

import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.evolboot.core.util.Assert;
import org.springframework.stereotype.Service;

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
        Assert.notNull(imageCaptcha, CaptchaI18nMessage.ImageCaptcha.codeError());
        boolean result = imageCaptcha.verify(code);
        repository.deleteByToken(token);
        return result;
    }
}

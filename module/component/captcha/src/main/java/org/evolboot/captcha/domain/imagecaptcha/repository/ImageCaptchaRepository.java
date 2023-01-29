package org.evolboot.captcha.domain.imagecaptcha.repository;

import org.evolboot.captcha.domain.imagecaptcha.ImageCaptcha;

/**
 * @author evol
 */

public interface ImageCaptchaRepository {

    ImageCaptcha save(ImageCaptcha imageCaptcha);

    ImageCaptcha findByToken(String token);

    void deleteByToken(String token);

}

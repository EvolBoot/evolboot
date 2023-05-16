package org.evolboot.captcha.domain.imagecaptcha.repository;

import org.evolboot.captcha.domain.imagecaptcha.ImageCaptcha;
import org.evolboot.core.data.BaseRepository;

/**
 * @author evol
 */

public interface ImageCaptchaRepository extends BaseRepository<ImageCaptcha, Long> {

    ImageCaptcha save(ImageCaptcha imageCaptcha);

    ImageCaptcha findByToken(String token);

    void deleteByToken(String token);

}

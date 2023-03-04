package org.evolboot.captcha.domain.imagecaptcha;

import org.evolboot.captcha.domain.imagecaptcha.service.ImageCaptchaCreateFactory;

/**
 * @author evol
 */

public interface ImageCaptchaAppService {

    ImageCaptchaCreateFactory.Response create(Integer width, Integer height, String ip);

    boolean verify(String token, String codeValue);

    void verifyIsTrue(String token, String codeValue);
}


package org.evolboot.captcha.domain.imagecaptcha;

/**
 * @author evol
 */

public interface ImageCaptchaAppService {

    ImageCaptchaCreateFactory.Response create(Integer width, Integer height, String ip);

    boolean verify(String token, String codeValue);

    void verifyIsTrue(String token, String codeValue);
}


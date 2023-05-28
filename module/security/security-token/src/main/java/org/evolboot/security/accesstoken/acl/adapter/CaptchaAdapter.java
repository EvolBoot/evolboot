package org.evolboot.security.accesstoken.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaAppService;
import org.evolboot.security.accesstoken.acl.client.CaptchaClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class CaptchaAdapter implements CaptchaClient {

    private final MobileCaptchaAppService mobileCaptchaAppService;

    private final ImageCaptchaAppService imageCaptchaAppService;

    public CaptchaAdapter(MobileCaptchaAppService mobileCaptchaAppService, ImageCaptchaAppService imageCaptchaAppService) {
        this.mobileCaptchaAppService = mobileCaptchaAppService;
        this.imageCaptchaAppService = imageCaptchaAppService;
    }

    @Override
    public void verifyMobileCaptchaIsTrue(String mobilePrefix, String mobile, String mobileCaptchaCode, String mobileCaptchaToken) {
        mobileCaptchaAppService.verifyIsTrue(mobilePrefix, mobile, mobileCaptchaToken, mobileCaptchaCode, null);
    }

    @Override
    public void verifyImageCaptchaIsTrue(String token, String code) {
        imageCaptchaAppService.verifyIsTrue(token, code);
    }
}

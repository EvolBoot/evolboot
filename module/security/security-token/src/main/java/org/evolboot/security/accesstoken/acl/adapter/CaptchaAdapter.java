package org.evolboot.security.accesstoken.acl.adapter;

import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaAppService;
import org.evolboot.security.accesstoken.acl.port.CaptchaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class CaptchaAdapter implements CaptchaClient {

    private final MobileCaptchaAppService mobileCaptchaAppService;

    public CaptchaAdapter(MobileCaptchaAppService mobileCaptchaAppService) {
        this.mobileCaptchaAppService = mobileCaptchaAppService;
    }

    @Override
    public void verifyMobileCaptchaIsTrue(String mobilePrefix, String mobile, String mobileCaptchaCode, String mobileCaptchaToken) {
        mobileCaptchaAppService.verifyIsTrue(mobilePrefix, mobile, mobileCaptchaToken, mobileCaptchaCode, null);
    }
}

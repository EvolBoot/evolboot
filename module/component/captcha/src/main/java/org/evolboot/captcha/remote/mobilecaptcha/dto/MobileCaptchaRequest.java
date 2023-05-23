package org.evolboot.captcha.remote.mobilecaptcha.dto;

import lombok.Data;
import org.evolboot.captcha.domain.mobilecaptcha.service.MobileCaptchaCreateFactory;
import org.evolboot.shared.sms.SmsMessageTag;

/**
 * @author evol
 */
@Data
public class MobileCaptchaRequest {
    private String mobilePrefix;
    private String mobile;
    private SmsMessageTag messageTag;
    private String imageCaptchaToken;
    private String imageCaptchaCode;


    public MobileCaptchaCreateFactory.Request to(String ip) {
        return MobileCaptchaCreateFactory.Request.builder()
                .mobilePrefix(mobilePrefix)
                .mobile(mobile)
                .messageTag(messageTag)
                .imageCaptchaCode(imageCaptchaCode)
                .imageCaptchaToken(imageCaptchaToken)
                .verifyImageCaptcha(true)
                .ip(ip)
                .build();
    }

}

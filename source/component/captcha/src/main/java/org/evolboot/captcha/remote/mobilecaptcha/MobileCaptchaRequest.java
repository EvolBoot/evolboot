package org.evolboot.captcha.remote.mobilecaptcha;

import org.evolboot.captcha.domain.mobilecaptcha.MobileCaptchaCreateFactory;
import org.evolboot.shared.sms.MessageTag;
import lombok.Data;

/**
 * @author evol
 * 
 */
@Data
public class MobileCaptchaRequest {
    private String mobilePrefix;
    private String mobile;
    private MessageTag messageTag;
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

package org.evolboot.captcha.remote.mobilecaptcha;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.captcha.domain.mobilecaptcha.entity.MobileCaptcha;

/**
 * @author evol
 */
@Getter
@Builder
public class MobileCaptchaLoginResponse {
    private String mobile;
    private String token;
    private Long interval;
    private Long expires;

    public static MobileCaptchaLoginResponse of(MobileCaptcha mobileCaptcha) {

        return MobileCaptchaLoginResponse.builder()
                .mobile(mobileCaptcha.getMobile())
                .token(mobileCaptcha.getToken())
                .interval(mobileCaptcha.getInterval())
                .expires(mobileCaptcha.getRemainExpires())
                .build();
    }
}

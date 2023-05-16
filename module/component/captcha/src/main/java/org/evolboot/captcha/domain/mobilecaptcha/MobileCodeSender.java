package org.evolboot.captcha.domain.mobilecaptcha;

import lombok.Builder;
import lombok.Getter;

/**
 * 手机验证码发送
 *
 * @author evol
 */
public interface MobileCodeSender {

    Response send(String mobile, String code);

    @Builder
    @Getter
    class Response {
        boolean sendStatus;
        String smsContent;
    }
}

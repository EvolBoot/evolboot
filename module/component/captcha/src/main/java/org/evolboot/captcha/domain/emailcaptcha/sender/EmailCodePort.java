package org.evolboot.captcha.domain.emailcaptcha.sender;

import lombok.Builder;
import lombok.Getter;

/**
 * 邮箱验证码发送
 *
 * @author evol
 * 
 */
public interface EmailCodePort {

    Response send(String email, String code);

    @Builder
    @Getter
    class Response {
        boolean sendStatus;
    }
}

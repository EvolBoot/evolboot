package org.evolboot.captcha.acl.client;

import lombok.Builder;
import lombok.Getter;

/**
 * 邮箱验证码发送
 *
 * @author evol
 */
public interface EmailCodeClient {

    Response send(String email, String code);

    @Builder
    @Getter
    class Response {
        boolean sendState;
    }
}

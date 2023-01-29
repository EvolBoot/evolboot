package org.evolboot.captcha.remote.emailcaptcha;

import org.evolboot.captcha.domain.emailcaptcha.EmailCaptchaCreateFactory;
import org.evolboot.shared.email.MessageTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author evol
 */
@Data
public class EmailCaptchaLoginRequest {
    @Schema(defaultValue = "邮箱", example = "admin@qq.com")
    private String email;
    private MessageTag messageTag;
    private String imageCaptchaToken;
    private String imageCaptchaCode;

    public EmailCaptchaCreateFactory.Request to(String ip) {
        return EmailCaptchaCreateFactory.Request.builder()
                .email(email)
                .ip(ip)
                .imageCaptchaCode(imageCaptchaCode)
                .imageCaptchaToken(imageCaptchaToken)
                .messageTag(messageTag)
                .build();
    }
}

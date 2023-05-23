package org.evolboot.captcha.remote.emailcaptcha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.evolboot.captcha.domain.emailcaptcha.service.EmailCaptchaCreateFactory;
import org.evolboot.shared.email.EmailMessageTag;

/**
 * @author evol
 */
@Data
public class EmailCaptchaLoginRequest {
    @Schema(defaultValue = "邮箱", example = "admin@qq.com")
    private String email;
    private EmailMessageTag messageTag;
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

package org.evolboot.captcha.remote.emailcaptcha.dto;

import lombok.Data;

/**
 * @author evol
 */
@Data
public class EmailVerifyRequest {
    String token;
    String email;
    String code;
}

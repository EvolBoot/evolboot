package org.evolboot.captcha.remote.mobilecaptcha;

import lombok.Data;

/**
 * @author evol
 */
@Data
public class MobileVerifyRequest {
    String token;
    String mobile;
    String code;
}

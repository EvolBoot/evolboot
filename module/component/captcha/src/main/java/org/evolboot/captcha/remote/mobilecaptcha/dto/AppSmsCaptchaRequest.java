package org.evolboot.captcha.remote.mobilecaptcha.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
public class AppSmsCaptchaRequest {

    private String prefix;

    private String mobile;


}

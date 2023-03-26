package org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha;

import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.lang.DeviceType;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class MobileCaptchaAuthenticationToken implements AuthenticationToken {

    private String mobilePrefix;

    private String mobile;

    private String captchaToken;

    private String captchaCode;

    private DeviceType deviceType;

    private String ip;


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.MOBILE_CAPTCHA;
    }
}

package org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.shared.lang.DeviceType;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class MobileCaptchaAuthenticationToken implements AuthenticationToken {

    private String mobilePrefix;

    private String mobile;

    private String mobileCaptchaToken;

    private String mobileCaptchaCode;

    private DeviceType deviceType;

    private String ip;


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.MOBILE_CAPTCHA;
    }
}

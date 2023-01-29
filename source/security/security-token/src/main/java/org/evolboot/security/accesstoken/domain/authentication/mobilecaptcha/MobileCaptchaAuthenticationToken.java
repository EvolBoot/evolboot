package org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha;

import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

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


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.MOBILE_CAPTCHA;
    }
}

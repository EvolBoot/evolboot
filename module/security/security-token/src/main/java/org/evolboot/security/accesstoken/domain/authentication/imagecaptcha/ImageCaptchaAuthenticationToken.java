package org.evolboot.security.accesstoken.domain.authentication.imagecaptcha;

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
public class ImageCaptchaAuthenticationToken implements AuthenticationToken {

    private String username;


    private String password;

    private String imageCaptchaToken;

    private String imageCaptchaCode;

    private DeviceType deviceType;

    private String ip;


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.IMAGE_CAPTCHA;
    }
}

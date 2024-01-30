package org.evolboot.security.accesstoken.domain.authentication.imagecaptcha;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.accesstoken.acl.client.CaptchaClient;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.evolboot.security.accesstoken.domain.AccessToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationProvider;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class ImageCaptchaAuthenticationProvider implements AuthenticationProvider {

    private final IdentityClient identityClient;
    private final CaptchaClient captchaClient;

    public ImageCaptchaAuthenticationProvider(IdentityClient identityClient, CaptchaClient captchaClient) {
        this.identityClient = identityClient;
        this.captchaClient = captchaClient;
    }

    @Override
    public AccessToken authenticate(AuthenticationToken authenticationToken) {
        ImageCaptchaAuthenticationToken token = (ImageCaptchaAuthenticationToken) authenticationToken;
        captchaClient.verifyImageCaptchaIsTrue(token.getImageCaptchaToken(), token.getImageCaptchaCode());
        IdentityClient.UserInfo userInfo = identityClient.findByUsernameOrMobileOrEmailAndEncodePassword(token.getUsername(), token.getPassword());
        return new AccessToken(userInfo.getUserId(), userInfo.getNickname(), userInfo.getAuthorities());
    }


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.IMAGE_CAPTCHA;
    }
}

package org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha;

import org.evolboot.security.accesstoken.AccessTokenI18nMessage;
import org.evolboot.security.accesstoken.acl.client.CaptchaClient;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.evolboot.security.accesstoken.domain.AccessToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationProvider;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.security.accesstoken.exception.AccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class MobileCaptchaAuthenticationProvider implements AuthenticationProvider {

    private final IdentityClient identityClient;
    private final CaptchaClient captchaClient;

    public MobileCaptchaAuthenticationProvider(IdentityClient identityClient, CaptchaClient captchaClient) {

        this.identityClient = identityClient;
        this.captchaClient = captchaClient;
    }

    @Override
    public AccessToken authenticate(AuthenticationToken authenticationToken) {
        MobileCaptchaAuthenticationToken token = (MobileCaptchaAuthenticationToken) authenticationToken;
        captchaClient.verifyMobileCaptchaIsTrue(token.getMobilePrefix(), token.getMobile(), token.getMobileCaptchaCode(), token.getMobileCaptchaToken());
        try {
            IdentityClient.UserInfo userInfo = identityClient.findByMobile(token.getMobile());
            return new AccessToken(userInfo.getUserId(), userInfo.getAuthorities());
        } catch (Exception e) {
            throw new AccessTokenException(AccessTokenI18nMessage.authenticationError());
        }
    }


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.MOBILE_CAPTCHA;
    }
}

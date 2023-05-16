package org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.identity.domain.user.UserRegisterService;
import org.evolboot.security.accesstoken.AccessTokenI18nMessage;
import org.evolboot.security.accesstoken.acl.client.IdentityClient;
import org.evolboot.security.accesstoken.domain.AccessToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationProvider;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.security.accesstoken.exception.AccessTokenException;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class MobileCaptchaAuthenticationProvider implements AuthenticationProvider {

    private final IdentityClient identityClient;

    public MobileCaptchaAuthenticationProvider(IdentityClient identityClient) {
        this.identityClient = identityClient;
    }

    @Override
    public AccessToken authenticate(AuthenticationToken authenticationToken) {
        MobileCaptchaAuthenticationToken token = (MobileCaptchaAuthenticationToken) authenticationToken;
        try {
            IdentityClient.UserInfo userInfo = identityClient.findByMobileAndSmsCode(new UserRegisterService.Request(
                    token.getMobilePrefix(),
                    token.getMobile(),
                    null,
                    token.getCaptchaToken(),
                    token.getCaptchaCode(),
                    null,
                    token.getIp(),
                    null,
                    token.getDeviceType()
            ));
            return new AccessToken(userInfo.getUserId(), userInfo.getAuthorities());
        } catch (Exception e) {
            throw new AccessTokenException(I18NMessageHolder.message(AccessTokenI18nMessage.AUTHENTICATION_ERROR));
        }
    }


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.MOBILE_CAPTCHA;
    }
}

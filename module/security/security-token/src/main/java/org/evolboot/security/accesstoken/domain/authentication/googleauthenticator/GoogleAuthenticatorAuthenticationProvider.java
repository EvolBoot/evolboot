package org.evolboot.security.accesstoken.domain.authentication.googleauthenticator;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.GoogleAuthenticator;
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
public class GoogleAuthenticatorAuthenticationProvider implements AuthenticationProvider {

    private final IdentityClient identityClient;

    public GoogleAuthenticatorAuthenticationProvider(IdentityClient identityClient) {
        this.identityClient = identityClient;
    }

    @Override
    public AccessToken authenticate(AuthenticationToken authenticationToken) {
        GoogleAuthenticatorAuthenticationToken token = (GoogleAuthenticatorAuthenticationToken) authenticationToken;
        IdentityClient.UserInfo userInfo = identityClient.findByUsernameOrMobileOrEmailAndEncodePassword(token.getUsername(), token.getEncodePassword());
        String googleAuthenticatorSecret = userInfo.getGoogleAuthenticatorSecret();
        if (userInfo.getEnableGoogleAuthenticator()) {
            Boolean authcode = GoogleAuthenticator.authcode(token.getGoogleAuthenticatorCode(), googleAuthenticatorSecret);
            Assert.isTrue(authcode, "谷歌验证码错误");
        }
        return new AccessToken(userInfo.getUserId(), userInfo.getAuthorities());

    }


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.GOOGLE_AUTHENTICATOR;
    }
}

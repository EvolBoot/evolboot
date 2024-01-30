package org.evolboot.security.accesstoken.domain.authentication.usernamepassword;

import lombok.extern.slf4j.Slf4j;
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
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final IdentityClient identityClient;

    public UsernamePasswordAuthenticationProvider(IdentityClient identityClient) {

        this.identityClient = identityClient;
    }

    @Override
    public AccessToken authenticate(AuthenticationToken authenticationToken) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authenticationToken;
        try {
            IdentityClient.UserInfo userInfo = identityClient.findByUsernameOrMobileOrEmailAndEncodePassword(token.getUsername(), token.getEncodePassword());
            return new AccessToken(userInfo.getUserId(), userInfo.getNickname(), userInfo.getAuthorities());
        } catch (Exception e) {
            throw new AccessTokenException(AccessTokenI18nMessage.authenticationError());
        }
    }


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.USERNAME_EMAIL_MOBILE;
    }
}

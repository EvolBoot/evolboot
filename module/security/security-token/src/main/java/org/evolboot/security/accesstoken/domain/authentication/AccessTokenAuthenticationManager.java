package org.evolboot.security.accesstoken.domain.authentication;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.accesstoken.domain.AccessToken;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 认证入口
 *
 * @author evol
 */
@Component
@Slf4j
public class AccessTokenAuthenticationManager {

    private final Map<AuthenticationTokenType, AuthenticationProvider> providers;

    public AccessTokenAuthenticationManager(Map<AuthenticationTokenType, AuthenticationProvider> providers) {
        this.providers = providers;
    }

    public AccessToken authenticate(AuthenticationToken token) {
        return providers.get(token.getAuthenticationTokenType()).authenticate(token);
    }

}

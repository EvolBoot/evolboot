package org.evolboot.security.accesstoken.domain.authentication;

import org.evolboot.security.accesstoken.domain.AccessToken;

/**
 * 认证提供商
 *
 * @author evol
 */
public interface AuthenticationProvider {


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     */
    AccessToken authenticate(AuthenticationToken authenticationToken);

    AuthenticationTokenType getAuthenticationTokenType();


}

package org.evolboot.security.accesstoken.domain.authentication.usernamepassword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
public class UsernamePasswordAuthenticationToken implements AuthenticationToken {

    private String username;
    private String encodePassword;


    @Override
    public AuthenticationTokenType getAuthenticationTokenType() {
        return AuthenticationTokenType.USERNAME_EMAIL_MOBILE;
    }
}

package org.evolboot.security.api.filter;

import org.apache.commons.lang3.StringUtils;
import org.evolboot.shared.security.EvolSession;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author evol
 */
public class SecurityAccessTokenConverter {

    private static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
    private static final String REQUEST_PARAMETER_TOKEN_NAME = "token";

    public static String convert(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (Objects.nonNull(token)) {
            token = token.trim();
            if (!StringUtils.startsWithIgnoreCase(token, AUTHENTICATION_SCHEME_BEARER)) {
                return null;
            }
            token = StringUtils.substring(token, 6);
        }

        if (Objects.isNull(token)) {
            token = request.getParameter(REQUEST_PARAMETER_TOKEN_NAME);
        }
        return StringUtils.trim(token);
    }

    public static Authentication convert(String token, EvolSession evolSession) {
        return new DefaultAuthentication(token, evolSession);
    }

    public static class DefaultAuthentication extends AbstractAuthenticationToken {

        private Object credentials;

        private Object principal;

        public DefaultAuthentication(String token, EvolSession evolSession) {
            super(CollectionUtils.isEmpty(evolSession.getAuthorities())
                    ? AuthorityUtils.NO_AUTHORITIES
                    : AuthorityUtils.createAuthorityList(evolSession.getAuthorities().toArray(new String[0])));
            this.principal = evolSession;
            this.credentials = token;
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public Object getCredentials() {
            return credentials;
        }

        @Override
        public Object getPrincipal() {
            return principal;
        }
    }


}

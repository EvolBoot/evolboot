package org.evolboot.security.api.filter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.api.EvolSession;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.security.api.autoconfigure.SecurityDefaultConfigProperties;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author evol
 */
@Slf4j
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityAccessTokenAppService securityAccessTokenAppService;
    private final SecurityDefaultConfigProperties securityDefaultConfigProperties;

    public AccessTokenAuthenticationFilter(SecurityAccessTokenAppService securityAccessTokenAppService, SecurityDefaultConfigProperties securityDefaultConfigProperties) {
        this.securityAccessTokenAppService = securityAccessTokenAppService;
        this.securityDefaultConfigProperties = securityDefaultConfigProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final boolean debug = this.logger.isDebugEnabled();

        try {
            String tokenValue = SecurityAccessTokenConverter.convert(request);

            if (tokenValue == null) {
                return;
            }

            // 测试模式下
            if (securityDefaultConfigProperties.getTestMode() && tokenValue.startsWith(securityDefaultConfigProperties.getTestKey())) {
                Long userId = Long.parseLong(tokenValue.replace(securityDefaultConfigProperties.getTestKey() + "_", ""));
                EvolSession accessToken = new EvolSession(userId);
                accessToken.setAuthorities(Sets.newHashSet(UserIdentity.ROLE_ADMIN.name(), UserIdentity.ROLE_MEMBER.name()));
                Authentication authentication = SecurityAccessTokenConverter.convert(tokenValue, accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return;
            }


            EvolSession evolSession = securityAccessTokenAppService.findByToken(tokenValue);

            if (evolSession == null) {
                return;
            }

            if (debug) {
                this.logger
                        .debug("Token Authentication Authorization header found for token '"
                                + evolSession.getPrincipalId() + "'");
            }

            Authentication authentication = SecurityAccessTokenConverter.convert(tokenValue, evolSession);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            if (debug) {
                this.logger.debug("Authentication request for failed: " + failed);
            }
            failed.printStackTrace();
        } finally {
            chain.doFilter(request, response);
        }
    }


}

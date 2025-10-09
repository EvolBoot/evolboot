package org.evolboot.security.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.security.CurrentSessionHolder;
import org.evolboot.shared.security.EvolSession;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.security.api.autoconfigure.SecurityDefaultConfigProperties;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Slf4j
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityAccessTokenAppService securityAccessTokenAppService;
    private final SecurityDefaultConfigProperties securityDefaultConfigProperties;

    private final HashSet<String> ALL_USER_IDENTITY = new HashSet<>(Arrays.stream(UserIdentity.VALUES).map(UserIdentity::name).collect(Collectors.toList()));

    public AccessTokenAuthenticationFilter(SecurityAccessTokenAppService securityAccessTokenAppService, SecurityDefaultConfigProperties securityDefaultConfigProperties) {
        this.securityAccessTokenAppService = securityAccessTokenAppService;
        this.securityDefaultConfigProperties = securityDefaultConfigProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final boolean debug = this.logger.isDebugEnabled();
        EvolSession evolSession;
        try {
            String tokenValue = SecurityAccessTokenConverter.convert(request);

            if (tokenValue == null) {
                return;
            }

            // 测试模式下
            if (securityDefaultConfigProperties.getTestMode() && tokenValue.startsWith(securityDefaultConfigProperties.getTestKey())) {
                Long userId = Long.parseLong(tokenValue.replace(securityDefaultConfigProperties.getTestKey() + "_", ""));
                evolSession = new EvolSession(userId, "Test");
                evolSession.setAuthorities(ALL_USER_IDENTITY);
                setAuthentication(tokenValue, evolSession);
                return;
            }

            evolSession = securityAccessTokenAppService.findByToken(tokenValue);

            if (evolSession == null) {
                return;
            }

            if (debug) {
                this.logger
                        .debug("Token Authentication Authorization header found for token '"
                                + evolSession.getUserId() + "'");
            }
            setAuthentication(tokenValue, evolSession);

        } catch (AuthenticationException failed) {
            clearContext();
            if (debug) {
                this.logger.debug("Authentication request for failed: " + failed);
            }
            failed.printStackTrace();
        } finally {
            chain.doFilter(request, response);
            // 清除
            log.debug("清除 SecurityContextHolder.clearContext();");
            clearContext();
        }
    }

    /**
     * 设置授权上下文
     *
     * @param tokenValue
     * @param evolSession
     */
    private static void setAuthentication(String tokenValue, EvolSession evolSession) {
        Authentication authentication = SecurityAccessTokenConverter.convert(tokenValue, evolSession);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CurrentSessionHolder.setContextHolder(evolSession);
    }

    /**
     * 清理上下文
     */
    private static void clearContext() {
        SecurityContextHolder.clearContext();
        CurrentSessionHolder.clearContext();
    }


}

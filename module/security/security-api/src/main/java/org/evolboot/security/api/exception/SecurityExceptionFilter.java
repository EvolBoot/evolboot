package org.evolboot.security.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 因为有些错误只能经过过滤器，所以用此过滤器
 *
 * @author evol
 */
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionFilter extends GenericFilterBean {

    private final SecurityApiExceptionHandler securityApiExceptionHandler;

    public SecurityExceptionFilter(SecurityApiExceptionHandler securityApiExceptionHandler) {
        this.securityApiExceptionHandler = securityApiExceptionHandler;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (RequestRejectedException e) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            securityApiExceptionHandler.resolveException(request, response, null, e);
            if (log.isWarnEnabled()) {
                log
                        .warn(
                                "request_rejected: remote={}, user_agent={}, request_url={}",
                                request.getRemoteHost(),
                                request.getHeader(HttpHeaders.USER_AGENT),
                                request.getRequestURL(),
                                e
                        );
            }
        }
    }

}

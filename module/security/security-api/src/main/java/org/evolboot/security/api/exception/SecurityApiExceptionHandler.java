package org.evolboot.security.api.exception;


import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.Constant;
import org.evolboot.core.exception.DefaultCoreHandlerExceptionResolver;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.security.api.SecurityI18nMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.evolboot.core.Constant.ErrorStatusCode.ACCESS_DENIED_CODE;
import static org.evolboot.core.Constant.ErrorStatusCode.NOT_FOUND_CODE;


/**
 * 注解拦截器
 *
 * @author evol
 */
@Component
@Slf4j
public class SecurityApiExceptionHandler extends DefaultCoreHandlerExceptionResolver {

    public SecurityApiExceptionHandler() {
        super();
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_0);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof AccessTokenExpiredException) {
            return handle((AccessTokenExpiredException) ex, request, response, handler);
        } else if (ex instanceof AccessDeniedException) {
            return handle((AccessDeniedException) ex, request, response, handler);
        } else if (ex instanceof RequestRejectedException) {
            return handle((RequestRejectedException) ex, request, response, handler);
        }
        return null;
    }

    protected ModelAndView handle(AccessTokenExpiredException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ACCESS_DENIED_CODE, I18NMessageHolder.message(SecurityI18nMessage.accessIsDenied), ex);
        return modelAndView;
    }

    protected ModelAndView handle(RequestRejectedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, NOT_FOUND_CODE, "Not Found", ex);
        return modelAndView;
    }

    protected ModelAndView handle(AccessDeniedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ACCESS_DENIED_CODE, I18NMessageHolder.message(SecurityI18nMessage.accessIsDenied), ex);
        return modelAndView;
    }


}

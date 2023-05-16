package org.evolboot.security.accesstoken.exception;


import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.Constant;
import org.evolboot.core.exception.DefaultCoreHandlerExceptionResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.evolboot.core.Constant.ErrorStatusCode.ERROR_CODE;


/**
 * 注解拦截器
 *
 * @author evol
 */
@Component
@Slf4j
public class AccessTokenExceptionHandler extends DefaultCoreHandlerExceptionResolver {

    public AccessTokenExceptionHandler() {
        super();
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_0);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof AccessTokenException) {
            return handle((AccessTokenException) ex, request, response, handler);
        }
        return null;
    }


    protected ModelAndView handle(AccessTokenException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, ex.getMessage(), ex);
        return modelAndView;
    }

}

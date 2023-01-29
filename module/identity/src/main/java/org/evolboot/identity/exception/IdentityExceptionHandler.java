package org.evolboot.identity.exception;


import org.evolboot.core.Constant;
import org.evolboot.core.exception.DefaultCoreHandlerExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.evolboot.core.Constant.ErrorStatusCode.ERROR_CODE;


/**
 * 注解拦截器
 *
 * @author evol
 * 
 */
@Component
@Slf4j
public class IdentityExceptionHandler extends DefaultCoreHandlerExceptionResolver {

    public IdentityExceptionHandler() {
        super();
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_0);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof IdentityExceptoin) {
            return handle((IdentityExceptoin) ex, request, response, handler);
        }
        return null;
    }

    protected ModelAndView handle(IdentityExceptoin ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, ex.getMessage(), ex);
        return modelAndView;
    }


}

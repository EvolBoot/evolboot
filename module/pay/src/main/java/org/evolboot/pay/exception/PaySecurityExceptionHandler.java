package org.evolboot.pay.exception;


import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.Constant;
import org.evolboot.core.exception.handler.DefaultCoreHandlerExceptionResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * 注解拦截器
 *
 * @author evol
 */
@Component
@Slf4j
public class PaySecurityExceptionHandler extends DefaultCoreHandlerExceptionResolver {

    public PaySecurityExceptionHandler() {
        super();
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_0);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof PayException) {
            return handle((PayException) ex, request, response, handler);
        }
        return null;
    }


    protected ModelAndView handle(PayException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getErrorCode(), ex.getMessage(), ex);
        return modelAndView;
    }


}

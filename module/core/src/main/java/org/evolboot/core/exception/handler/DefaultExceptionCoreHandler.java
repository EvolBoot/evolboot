package org.evolboot.core.exception.handler;


import org.evolboot.core.Constant;
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
public class DefaultExceptionCoreHandler extends DefaultCoreHandlerExceptionResolver {

    public DefaultExceptionCoreHandler() {
        super();
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_1);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.resolveException(request, response, handler, ex);
    }
}

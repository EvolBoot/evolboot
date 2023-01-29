package org.evolboot.core.autoconfigure;

import org.evolboot.core.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import java.util.List;

/**
 * @author evol
 */
@Configuration
public class HandlerExceptionConfiguration {

    /**
     * 因为 HandlerExceptionResolverComposite 优先级过高，需要往下调一调，免得和  DefaultExceptionCoreHandler 冲突
     *
     * @param resolvers
     */
    public HandlerExceptionConfiguration(List<HandlerExceptionResolver> resolvers) {
        resolvers.forEach(resolver -> {
            if (resolver instanceof HandlerExceptionResolverComposite) {
                ((HandlerExceptionResolverComposite) resolver).setOrder(Constant.HandlerExceptionResolverOrder.ORDER_2);
            }
        });
    }
}

package org.evolboot.core.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.DomainEventPublisher;
import org.evolboot.core.event.EventConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class SpringEventPublisherConfiguration {


    public SpringEventPublisherConfiguration(DomainEventPublisher domainEventPublisher) {
        initEventPublisher(domainEventPublisher);
    }

    private void initEventPublisher(DomainEventPublisher domainEventPublisher) {
        log.info("事件发布器初始化");
        EventConfig.config(domainEventPublisher);
    }


}

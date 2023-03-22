package org.evolboot.mq.rocketmq.consumer;

import org.evolboot.core.event.EventPublisher;
import org.evolboot.shared.event.mq.RocketMQMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
@Slf4j
public class LocalTransactionManager {

    private final EventPublisher eventPublisher;

    public LocalTransactionManager(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public <T extends RocketMQMessage> void notifyByTransaction(T message) {
        eventPublisher.publishEvent(message);
    }

}

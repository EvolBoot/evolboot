package org.evolboot.mq.rocketmq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.event.mq.TestMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class TestListener {


    @EventListener
    public void on(TestMessage message) {
        log.info("收到消息:{},{}", message.getMessage());
    }

}

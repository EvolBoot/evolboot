package org.evolboot.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.mq.MQMessagePublisher;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.MQMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class KakfaMessagePublisher implements MQMessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KakfaMessagePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public <T extends MQMessage> void send(T message) {
        String tag = message.getClass().getName();
        kafkaTemplate.send("kakfa-topic-test", tag, JsonUtil.stringify(message));

        kafkaTemplate.executeInTransaction(kafkaOperations -> {
            kafkaOperations.send("", "message");
            if ("".equals("")) {  // 如果回调条件满足
                return true;  // 提交事务
            } else {
                return false;  // 回滚事务
            }
        });
    }


}

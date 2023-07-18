package org.evolboot.mq.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class KafkaMQMessageConsumer {

    @KafkaListener(topics = "kakfa-topic-test", groupId = "group_id")
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println("Consumed message: " + key + ":" + message);
        throw new ExtendRuntimeException("测试");
    }

    @KafkaListener(topics = "your-dlq-topic", groupId = "group_id")
    public void listenToDlq(ConsumerRecord<String, String> record) {
        // 在这里处理来自 DLQ 的消息

        String messageKey = record.key();
        String messageValue = record.value();

        // 你可以打印消息的 key 和 value
        System.out.println("死信:Received message with key: " + messageKey + ", value: " + messageValue);
//        throw new ExtendRuntimeException("死信");
    }

}

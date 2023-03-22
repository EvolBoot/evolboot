package org.evolboot.mq.rocketmq.consumer;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.RocketMQMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者
 *
 * @author evol
 * 
 */
@Slf4j
@RocketMQMessageListener(topic = "${rocketmq.topic}", consumerGroup = "${rocketmq.group}")
@Service
public class RocketMQMessageConsumerImpl implements RocketMQListener<MessageExt> {


    private final LocalTransactionManager localTransactionManager;

    public RocketMQMessageConsumerImpl(LocalTransactionManager localTransactionManager) {
        this.localTransactionManager = localTransactionManager;
    }

    @Override
    public void onMessage(MessageExt message) {
        String tags = message.getTags();
        String msgId = message.getMsgId();
        log.info("收到消息:消息ID: {}, Tag: {},", msgId, tags);
        Class<?> messageClass = getMessageClass(tags);
        if (ExtendObjects.isNull(messageClass)) {
            log.info("不是所需的消息,消息ID: {}, Tag: {}", msgId, tags);
            return;
        }
        RocketMQMessage rocketMQMessage = (RocketMQMessage) JsonUtil.parse(new String(message.getBody()), messageClass);
        localTransactionManager.notifyByTransaction(rocketMQMessage);
    }

    private Class<?> getMessageClass(String messageClazz) {
        try {
            return Class.forName(messageClazz);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}

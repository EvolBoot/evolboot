package org.evolboot.mq.producer;

import org.evolboot.shared.event.mq.TransactionRocketMQMessage;
import org.evolboot.mq.producer.mqtransaction.MqTransactionAppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class CommonRocketMQLocalTransactionCheck implements RocketMQLocalTransactionCheck<TransactionRocketMQMessage<?>> {

    private final MqTransactionAppService service;

    public CommonRocketMQLocalTransactionCheck(MqTransactionAppService service) {
        this.service = service;
    }


    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(TransactionRocketMQMessage<?> message, int checkTimes) {
        log.info("MQ:通用事务检测器:ID:{},checkTimes:{}", message.getMqTransactionId(), checkTimes);
        if (checkTimes > 20) {
            log.info("大于20次的查询,回滚吧,放弃了:{},{}", message.getMqTransactionId(), message);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        if (service.findById(message.getMqTransactionId()).isPresent()) {
            log.info("MQ:通用事务检测器:ID:{},checkTimes:{},提交", message.getMqTransactionId(), checkTimes);
            return RocketMQLocalTransactionState.COMMIT;
        }
        log.info("MQ:通用事务检测器:没有查到,返回UNKNOWN,ID:{},checkTimes:{}", message.getMqTransactionId(), checkTimes);
        return RocketMQLocalTransactionState.UNKNOWN;
    }
}

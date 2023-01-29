package org.evolboot.mq.producer;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.RocketMQMessage;
import org.evolboot.shared.event.mq.TransactionRocketMQMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.util.Map;

import static org.evolboot.mq.producer.MqConstant.ROCKETMQ_TRANSACTION_ID;
import static org.evolboot.mq.producer.MqConstant.TRANSACTION_CHECK_TIMES;


/**
 * @author evol
 */
@Slf4j
@RocketMQTransactionListener
public class RocketMQLocalTransactionListenerImpl implements RocketMQLocalTransactionListener {

    private final Map<Class<RocketMQMessage>, RocketMQLocalTransactionCheck> map;
    private final CommonRocketMQLocalTransactionCheck commonRocketMQLocalTransactionCheck;

    public RocketMQLocalTransactionListenerImpl(
            Map<Class<RocketMQMessage>, RocketMQLocalTransactionCheck> map,
            CommonRocketMQLocalTransactionCheck commonRocketMQLocalTransactionCheck) {
        this.map = map;
        this.commonRocketMQLocalTransactionCheck = commonRocketMQLocalTransactionCheck;
    }

    @SneakyThrows
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String tag = (String) msg.getHeaders().get(MqConstant.TAG);
        String messageClassStr = tag;
        Class<RocketMQMessage> aClass = (Class<RocketMQMessage>) Class.forName(messageClassStr);
        RocketMQLocalTransactionCheck rocketMQLocalTransactionCheck = map.get(aClass);
        RocketMQMessage rocketMQMessage = JsonUtil.parse(new String((byte[]) msg.getPayload()), aClass);
        if (ExtendObjects.nonNull(rocketMQLocalTransactionCheck)) {
            log.info("检查本地事务,调用检查器进行事务检查:arg: {}, Source: {},CheckTimes:{}", arg, rocketMQMessage.getSource(), 0);
            return rocketMQLocalTransactionCheck.checkLocalTransaction(rocketMQMessage, 0);
        }
        if (rocketMQMessage instanceof TransactionRocketMQMessage) {
            log.info("检查本地事务,使用通用检查器进行事务检查:arg: {}, Source: {},CheckTimes:{}", arg, rocketMQMessage.getSource(), 0);
            return commonRocketMQLocalTransactionCheck.checkLocalTransaction((TransactionRocketMQMessage<?>) rocketMQMessage, 0);
        }
        log.info("没有处理事务的监听器,直接提交,arg: {}", arg);
        return RocketMQLocalTransactionState.COMMIT;
    }

    @SneakyThrows
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String tag = (String) msg.getHeaders().get(MqConstant.TAG);
        String id = (String) msg.getHeaders().get(ROCKETMQ_TRANSACTION_ID);
        log.info("检查本地事务,消息ID: {}, Tag: {}", id, tag);
        String messageClassStr = tag;
        int transactionCheckTimes = getTransactionCheckTimes(msg);
        Class<RocketMQMessage> aClass = (Class<RocketMQMessage>) Class.forName(messageClassStr);
        RocketMQMessage rocketMQMessage = JsonUtil.parse(new String((byte[]) msg.getPayload()), aClass);
        RocketMQLocalTransactionCheck rocketMQLocalTransactionCheck = map.get(aClass);
        if (ExtendObjects.nonNull(rocketMQLocalTransactionCheck)) {
            log.info("检查本地事务,调用检查器进行事务检查:消息ID: {}, Source: {},CheckTimes:{}", id, rocketMQMessage.getSource(), transactionCheckTimes);
            return rocketMQLocalTransactionCheck.checkLocalTransaction(rocketMQMessage, transactionCheckTimes);
        }
        if (rocketMQMessage instanceof TransactionRocketMQMessage) {
            log.info("检查本地事务,使用通用检查器进行事务检查:消息ID: {}, Source: {},CheckTimes:{}", id, rocketMQMessage.getSource(), transactionCheckTimes);
            return commonRocketMQLocalTransactionCheck.checkLocalTransaction((TransactionRocketMQMessage<?>) rocketMQMessage, transactionCheckTimes);
        }
        log.info("没有处理事务的监听器,直接提交,消息ID: {}", id);
        return RocketMQLocalTransactionState.COMMIT;
    }

    private int getTransactionCheckTimes(Message msg) {
        return Integer.valueOf((String) msg.getHeaders().get(TRANSACTION_CHECK_TIMES));
    }
}

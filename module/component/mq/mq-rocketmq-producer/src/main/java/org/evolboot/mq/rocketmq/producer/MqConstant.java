package org.evolboot.mq.rocketmq.producer;

/**
 * @author evol
 */
public interface MqConstant {

    String TAG = "tag";


    /**
     * 查询消息ID
     */
    String ROCKETMQ_TRANSACTION_ID = "rocketmq_TRANSACTION_ID";

    /**
     * 查询消息检查次数
     */
    String TRANSACTION_CHECK_TIMES = "TRANSACTION_CHECK_TIMES";


}

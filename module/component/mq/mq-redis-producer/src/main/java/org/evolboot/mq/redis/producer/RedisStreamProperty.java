package org.evolboot.mq.redis.producer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 */
@Configuration
@Getter
@Setter
public class RedisStreamProperty {

    @Value("${spring.redis.mq-stream.key}")
    private String key;

    private String keyForRealTime;

    private String keyForTransaction;

    private String keyForDelayTime;

    @Value("${spring.redis.mq-stream.group}")
    private String group;

    @Value("${spring.redis.mq-stream.consumer}")
    private String consumer;

    @Value("${spring.redis.mq-stream.thread-number}")
    private int threadNumber = 1;

    public int getThreadNumber() {
        if (threadNumber < 1) {
            threadNumber = 1;
        }
        return threadNumber;
    }


    /**
     * 实时消息
     * @return
     */
    public String getKeyForRealTime() {
        return key + "_real_time";
    }

    /**
     * 事务消息
     * @return
     */
    public String getKeyForTransaction() {
        return key + "_transaction";
    }

    /**
     * 延时消息
     * @return
     */
    public String getKeyForDelayTime() {
        return key + "_delay_time";
    }
}

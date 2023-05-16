package org.evolboot.shared.event.mq;


import lombok.Getter;
import lombok.Setter;

/**
 * 延时消息
 *
 * @author evol
 */

@Setter
@Getter
public abstract class DelayMQMessage<T> extends RocketMQMessage<T> {

    /**
     * 延时秒数
     */
    private long delayTimeSeconds;


}

package org.evolboot.shared.event.mq;


import lombok.Getter;
import lombok.Setter;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@Setter
public abstract class MQMessage<T> implements Event<T> {

    /**
     * 创建时间戳
     */
    private Long messageCreateTimestamp;

}

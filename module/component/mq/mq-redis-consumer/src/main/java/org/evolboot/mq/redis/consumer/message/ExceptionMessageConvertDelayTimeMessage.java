package org.evolboot.mq.redis.consumer.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.shared.event.mq.DelayMQMessage;

/**
 * 消息处理发生错误时，将转为延时消息处理
 *
 * @author evol
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class ExceptionMessageConvertDelayTimeMessage extends DelayMQMessage<String> {

    private String clazz;

    private String jsonContent;


    @Override
    public String getEventSourceId() {
        return jsonContent;
    }

}

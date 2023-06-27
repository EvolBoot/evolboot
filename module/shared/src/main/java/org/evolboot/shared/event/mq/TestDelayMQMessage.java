package org.evolboot.shared.event.mq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
@NoArgsConstructor
public class TestDelayMQMessage extends DelayMQMessage<String> {

    private String message;

    public TestDelayMQMessage(String message) {
        this.message = message;
    }

    @Override
    public String getEventSourceId() {
        return message;
    }
}

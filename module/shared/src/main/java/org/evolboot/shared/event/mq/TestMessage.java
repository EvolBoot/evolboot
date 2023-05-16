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
public class TestMessage extends RocketMQMessage<String> {

    private String message;

    public TestMessage(String message) {
        this.message = message;
    }

    @Override
    public String getSource() {
        return message;
    }
}

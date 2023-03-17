package org.evolboot.shared.event.mq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public class TestMessage implements RocketMQMessage<String> {

    private String message;

    public TestMessage(String message) {
        this.message = message;
    }

    @Override
    public String getSource() {
        return message;
    }
}

package org.evolboot.im.domain.chatrecord.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TextMessageContent implements MessageContent {

    private String text;

    @Override
    public MessageType getType() {
        return MessageType.TEXT;
    }
}

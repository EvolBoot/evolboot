package org.evolboot.im.domain.chatrecord.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
public class VoiceMessageContent implements MessageContent {


    @Override
    public MessageType getType() {
        return MessageType.VOICE;
    }
}

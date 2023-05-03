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
public class ImageMessageContent implements MessageContent {

    private String imagePath;

    private String snapshotImagePath;

    @Override
    public MessageType getType() {
        return MessageType.IMAGE;
    }
}

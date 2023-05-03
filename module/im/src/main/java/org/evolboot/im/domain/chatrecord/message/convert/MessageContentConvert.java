package org.evolboot.im.domain.chatrecord.message.convert;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.chatrecord.message.*;

/**
 * @author evol
 */
@Slf4j
public class MessageContentConvert {


    public static <M extends MessageContent> M convert(MessageType messageType, String value) {
        if (messageType == null || value == null) {
            log.error("IM:消息内容转换:转换失败:因为:messageType:{} 为空,或:value:{} 为空", messageType, value);
            return (M) new UnknownMessageContent();
        }
        if (MessageType.TEXT.equals(messageType)) {
            return (M) JsonUtil.parse(value, TextMessageContent.class);
        } else if (MessageType.IMAGE.equals(messageType)) {
            return (M) JsonUtil.parse(value, ImageMessageContent.class);
        } else if (MessageType.VOICE.equals(messageType)) {
            return (M) JsonUtil.parse(value, VoiceMessageContent.class);
        } else if (MessageType.VIDEO.equals(messageType)) {
            return (M) JsonUtil.parse(value, VideoMessageContent.class);
        }
        return (M) new UnknownMessageContent();

    }
}

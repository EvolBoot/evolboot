package org.evolboot.im.remote.chatrecord;


import org.evolboot.im.domain.chatrecord.SenderRole;
import org.evolboot.im.domain.chatrecord.message.ImageMessageContent;
import org.evolboot.im.domain.chatrecord.message.MessageContent;
import org.evolboot.im.domain.chatrecord.message.MessageType;
import org.evolboot.im.domain.chatrecord.message.TextMessageContent;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.shared.lang.DeviceType;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */

@Setter
@Getter
public class ChatRecordCreateRequest extends ChatRecordCreateFactory.Request {


    ImageMessageContent messageContent;


}

package org.evolboot.im.remote.chatrecord;


import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.chatrecord.message.ImageMessageContent;
import org.evolboot.im.domain.chatrecord.service.ChatRecordCreateFactory;

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

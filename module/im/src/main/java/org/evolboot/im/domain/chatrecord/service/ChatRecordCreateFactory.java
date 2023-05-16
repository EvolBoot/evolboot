package org.evolboot.im.domain.chatrecord.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.chatrecord.ChatRecord;
import org.evolboot.im.domain.chatrecord.SenderRole;
import org.evolboot.im.domain.chatrecord.message.MessageContent;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.shared.lang.DeviceType;
import org.springframework.stereotype.Service;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@Service
public class ChatRecordCreateFactory extends ChatRecordSupportService {
    protected ChatRecordCreateFactory(ChatRecordRepository repository) {
        super(repository);
    }

    public ChatRecord execute(Request request) {

        //TODO clientMsgId 排重
        ChatRecord chatRecord = new ChatRecord(
                request.getClientMsgId(),
                request.getSenderId(),
                request.getSenderRole(),
                request.getDeviceType(),
                request.getConversationType(),
                request.getConversationId(),
                request.getMessageContent()
        );
        repository.save(chatRecord);
        return chatRecord;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request<M extends MessageContent> {
        String clientMsgId;
        Long senderId;
        SenderRole senderRole;
        DeviceType deviceType;
        ConversationType conversationType;
        Long conversationId;
        M messageContent;
    }

}

package org.evolboot.im.domain.chatrecord.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.entity.SenderRole;
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
public class ChatRecordCreateFactory {

    private final ChatRecordRepository repository;
    private final ChatRecordSupportService supportService;

    protected ChatRecordCreateFactory(ChatRecordRepository repository, ChatRecordSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
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


    public static class Response {

    }

}

package org.evolboot.im.domain.chatrecord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.chatrecord.message.MessageContent;
import org.evolboot.im.domain.chatrecord.message.convert.MessageContentConvert;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.chatrecord.message.MessageStatus;
import org.evolboot.im.domain.chatrecord.message.MessageType;
import org.evolboot.shared.lang.DeviceType;

import javax.persistence.*;


/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Table(name = "evol_im_chat_record")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class ChatRecord<M extends MessageContent> extends JpaAbstractEntity<Long> implements AggregateRoot<ChatRecord> {

    @Id
    private Long id;

    private String clientMsgId;

    /**
     * 发送者
     */
    private Long senderId;

    /**
     * 发送者角色
     */
    private SenderRole senderRole;

    /**
     * 发送设备
     */
    private DeviceType deviceType;

    /**
     * 会话类型
     */
    private ConversationType conversationType;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 消息内容
     */
    @JsonIgnore
    @Column(name = "message_content_")
    private String messageContentValue;

    /**
     * 消息类型
     */
    private MessageType messageType;

    /**
     * 消息状态
     */
    private MessageStatus status = MessageStatus.NORMAL;

    /**
     * 撤回的消息存档
     */
    @JsonIgnore
    private String revokeMessageContentValue;

    @Transient
    private M messageContent;


    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public ChatRecord(String clientMsgId, Long senderId, SenderRole senderRole, DeviceType deviceType, ConversationType conversationType, Long conversationId, M messageContent) {
        generateId();
        this.clientMsgId = clientMsgId;
        this.senderId = senderId;
        this.senderRole = senderRole;
        this.deviceType = deviceType;
        this.conversationType = conversationType;
        this.conversationId = conversationId;
        this.messageType = messageContent.getType();
        this.messageContentValue = JsonUtil.stringify(messageContent);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public ChatRecord root() {
        return this;
    }

    public M getMessageContent() {
        if (messageContent == null) {
            this.messageContent = MessageContentConvert.convert(messageType, messageContentValue);
        }
        return messageContent;
    }
}

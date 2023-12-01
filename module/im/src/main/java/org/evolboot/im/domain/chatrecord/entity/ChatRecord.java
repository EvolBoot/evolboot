package org.evolboot.im.domain.chatrecord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.chatrecord.message.MessageContent;
import org.evolboot.im.domain.chatrecord.message.MessageState;
import org.evolboot.im.domain.chatrecord.message.MessageType;
import org.evolboot.im.domain.chatrecord.message.convert.MessageContentConvert;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.shared.lang.DeviceType;

import jakarta.persistence.*;


/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Table(name = "evoltb_im_chat_record")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "聊天记录")
public class ChatRecord extends JpaAbstractEntity<Long> implements AggregateRoot<ChatRecord> {

    @Id
    private Long id;

    @Schema(title = "客户端消息ID")
    private String clientMsgId;

    /**
     * 发送者
     */
    @Schema(title = "发送者")
    private Long senderId;

    /**
     * 发送者角色
     */
    @Schema(title = "发送者角色")
    private SenderRole senderRole;

    /**
     * 发送设备
     */
    @Schema(title = "发送设备")
    private DeviceType deviceType;

    /**
     * 会话类型
     */
    @Schema(title = "会话类型")
    private ConversationType conversationType;

    /**
     * 会话ID
     */
    @Schema(title = "会话ID")
    private Long conversationId;

    /**
     * 消息内容（不会返回给前端）
     */
    @Schema(title = "消息内容")
    @JsonIgnore
    @Column(name = "message_content_")
    private String messageContentValue;

    /**
     * 消息类型
     */
    @Schema(title = "消息类型")
    private MessageType messageType;

    /**
     * 消息状态
     */
    @Schema(title = "消息状态")
    private MessageState state = MessageState.NORMAL;

    /**
     * 撤回的消息存档
     */
    @JsonIgnore
    private String revokeMessageContentValue;

    /**
     * 消息内容，返回给前端
     */
    @Transient
    @Schema(title = "消息内容")
    private MessageContent messageContent;


    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public ChatRecord(String clientMsgId, Long senderId, SenderRole senderRole, DeviceType deviceType, ConversationType conversationType, Long conversationId, MessageContent messageContent) {
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

    public MessageContent getMessageContent() {
        if (messageContent == null) {
            this.messageContent = MessageContentConvert.convert(messageType, messageContentValue);
        }
        return messageContent;
    }
}

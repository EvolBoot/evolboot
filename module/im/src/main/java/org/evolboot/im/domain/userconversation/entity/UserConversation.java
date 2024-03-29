package org.evolboot.im.domain.userconversation.entity;

import com.google.common.collect.Sets;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.userconversation.repository.jpa.convert.UserConversationForbidTalkCauseSetConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Set;


/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Table(name = "evoltb_im_user_conversation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "用户会话")
public class UserConversation extends JpaAbstractEntity<Long> implements AggregateRoot<UserConversation> {

    @Id
    private Long id;


    /**
     *
     */
    @Schema(title = "拥有者")
    private Long ownerUserId;

    /**
     * 会话ID
     */
    @Schema(title = "会话ID")
    private Long conversationId;

    /**
     * 会话类型
     */
    @Schema(title = "会话类型")
    private ConversationType conversationType;

    /**
     * 状态
     */
    @Schema(title = "状态")
    private UserConversationState state = UserConversationState.NORMAL;

    /**
     * 禁言原因
     */
    @Schema(title = "禁言原因")
    @Convert(converter = UserConversationForbidTalkCauseSetConverter.class)
    private Set<UserConversationForbidTalkCause> forbidTalkCauses = Sets.newHashSet();

    /**
     * 群ID，如果会话类型为 GROUP，则此ID不为空
     */
    @Schema(title = "群ID")
    private Long groupId;

    /**
     * 朋友会话ID，如果类型为 SINGLE ，则此ID不为空
     */
    @Schema(title = "朋友会话ID，如果类型为 SINGLE ，则此ID不为空")
    private Long friendUserId;


    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public UserConversation(Long ownerUserId, Long conversationId, ConversationType conversationType, Long groupId, Long friendUserId) {
        generateId();

        this.groupId = groupId;
        this.friendUserId = friendUserId;
        this.ownerUserId = ownerUserId;
        this.conversationId = conversationId;
        this.conversationType = conversationType;
    }

    /**
     * 增加禁言原因，并切会话状态改为禁言
     *
     * @param forbidTalkCause
     */
    public void addForbidTalkCauses(UserConversationForbidTalkCause forbidTalkCause) {
        this.forbidTalkCauses.add(forbidTalkCause);
        this.state = UserConversationState.FORBID_TALK;
    }

    /**
     * 移除禁言原因，如果禁言原因为空，则恢复正常聊天
     *
     * @param forbidTalkCause
     */
    public void removeForbidTalkCauses(UserConversationForbidTalkCause forbidTalkCause) {
        this.forbidTalkCauses.remove(forbidTalkCause);
        if (this.forbidTalkCauses.isEmpty()) {
            this.state = UserConversationState.NORMAL;
        }
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserConversation root() {
        return this;
    }
}

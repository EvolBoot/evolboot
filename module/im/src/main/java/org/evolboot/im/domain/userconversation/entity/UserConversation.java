package org.evolboot.im.domain.userconversation.entity;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.userconversation.repository.jpa.convert.UserConversationForbidTalkCauseSetConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;


/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Table(name = "evol_im_user_conversation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class UserConversation extends JpaAbstractEntity<Long> implements AggregateRoot<UserConversation> {

    @Id
    private Long id;


    private Long ownerUserId;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 会话类型
     */
    private ConversationType conversationType;

    /**
     * 状态
     */
    private UserConversationStatus status = UserConversationStatus.NORMAL;

    /**
     * 禁言原因
     */
    @Convert(converter = UserConversationForbidTalkCauseSetConverter.class)
    private Set<UserConversationForbidTalkCause> forbidTalkCauses = Sets.newHashSet();

    /**
     * 群ID，如果会话类型为 GROUP，则此ID不为空
     */
    private Long groupId;

    /**
     * 朋友会话ID，如果类型为 SINGLE ，则此ID不为空
     */
    private Long friendUserId;


    /**
     * 备注
     */
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
        this.status = UserConversationStatus.FORBID_TALK;
    }

    /**
     * 移除禁言原因，如果禁言原因为空，则恢复正常聊天
     *
     * @param forbidTalkCause
     */
    public void removeForbidTalkCauses(UserConversationForbidTalkCause forbidTalkCause) {
        this.forbidTalkCauses.remove(forbidTalkCause);
        if (this.forbidTalkCauses.isEmpty()) {
            this.status = UserConversationStatus.NORMAL;
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

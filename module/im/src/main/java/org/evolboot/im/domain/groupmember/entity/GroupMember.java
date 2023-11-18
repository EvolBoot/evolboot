package org.evolboot.im.domain.groupmember.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Table(name = "evoltb_im_group_member")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class GroupMember extends JpaAbstractEntity<Long> implements AggregateRoot<GroupMember> {

    @Id
    private Long id;

    /**
     * 群ID
     */
    private Long groupId;

    /**
     * 用户ID
     */
    private Long memberUserId;

    /**
     * 群角色
     */
    private GroupMemberRole role;

    /**
     * 状态
     */
    private GroupMemberState state = GroupMemberState.NORMAL;

    /**
     *
     */
    private Long conversationId;

    /**
     * 禁言截止时间
     */
    private Date forbidTalkDeadline;


    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public GroupMember(Long groupId,
                       Long memberUserId,
                       Long conversationId,
                       GroupMemberRole role,
                       GroupMemberState state,
                       Date forbidTalkDeadline) {
        this.generateId();
        this.groupId = groupId;
        this.memberUserId = memberUserId;
        this.conversationId = conversationId;
        this.role = role;
        this.state = state;
        this.forbidTalkDeadline = forbidTalkDeadline;
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public GroupMember root() {
        return this;
    }
}

package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;


/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Table(name = "evol_im_group_member")
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
    private GroupMemberStatus status;





    /**
     * 禁言截止时间
     */
    private Date forbidTalkDeadline;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public GroupMember(String name) {
        //   setLocales(locales);
        generateId();
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

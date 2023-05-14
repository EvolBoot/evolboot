package org.evolboot.im.domain.group;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Table(name = "evol_im_group")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Group extends JpaAbstractEntity<Long> implements AggregateRoot<Group> {

    @Id
    private Long id;

    /**
     * 群主
     */
    private Long ownerUserId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群头像
     */
    private String avatar;

    /**
     * 群描述
     */
    private String description;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 群通知
     */
    private String notification;

    /**
     * 群状态
     */
    private GroupStatus status = GroupStatus.NEED_APPLY;

    /**
     * 群类型
     */
    private GroupType type = GroupType.NORMAL;


    /**
     * 禁言范围
     */
    private GroupForbidTalkScope forbidTalkScope = GroupForbidTalkScope.DONT;




    /**
     * 限制人数
     */
    private Short limit = 500;

    /**
     * 群备注
     */
    private String remark;


    public Group(Long id, Long ownerUserId, String name, String avatar, String description, Long conversationId) {
        this.id = id;
        this.ownerUserId = ownerUserId;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.conversationId = conversationId;
    }

    public static Long generateId() {
        return IdGenerate.longId();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Group root() {
        return this;
    }
}

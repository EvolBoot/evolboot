package org.evolboot.im.domain.group.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.RegexUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Table(name = "evoltb_im_group")
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
    private GroupApplyState applyState = GroupApplyState.NEED_APPLY;

    /**
     * 群类型
     */
    private GroupType type = GroupType.NORMAL;

    /**
     * 禁言范围
     */
    private GroupForbidTalkScope forbidTalkScope = GroupForbidTalkScope.NONE;

    /**
     * 禁言截止时间
     */
    private Date forbidTalkDeadline;

    /**
     * 群成员数量
     */
    private Short quantityOfMember = 0;

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
        setOwnerUserId(ownerUserId);
        setName(name);
        setAvatar(avatar);
        setDescription(description);
        this.conversationId = conversationId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public void setName(String name) {
        Assert.notBlank(name, "请填写群名称");
        this.name = name;
    }

    public void setAvatar(String avatar) {
        Assert.notBlank(avatar, "请上传群头像");
        Assert.isTrue(RegexUtil.checkURL(avatar), "请上传正确的头像");
        this.avatar = avatar;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMember() {
        this.quantityOfMember++;
    }

    public void reductionMember() {
        this.quantityOfMember--;
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

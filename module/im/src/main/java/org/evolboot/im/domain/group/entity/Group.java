package org.evolboot.im.domain.group.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.RegexUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Schema(title = "群组")
public class Group extends JpaAbstractEntity<Long> implements AggregateRoot<Group> {

    @Id
    private Long id;

    /**
     * 群主
     */
    @Schema(title = "群主")
    private Long ownerUserId;

    /**
     * 群名称
     */
    @Schema(title = "群名称")
    private String name;

    /**
     * 群头像
     */
    @Schema(title = "群头像")
    private String avatar;

    /**
     * 群描述
     */
    @Schema(title = "群描述")
    private String description;

    /**
     * 会话ID
     */
    @Schema(title = "会话ID")
    private Long conversationId;

    /**
     * 群通知
     */
    @Schema(title = "群通知")
    private String notification;

    /**
     * 群状态
     */
    @Schema(title = "群状态")
    private GroupApplyState applyState = GroupApplyState.NEED_APPLY;

    /**
     * 群类型
     */
    @Schema(title = "群类型")
    private GroupType type = GroupType.NORMAL;

    /**
     * 禁言范围
     */
    @Schema(title = "禁言范围")
    private GroupForbidTalkScope forbidTalkScope = GroupForbidTalkScope.NONE;

    /**
     * 禁言截止时间
     */
    @Schema(title = "禁言截止时间")
    private Date forbidTalkDeadline;

    /**
     * 群成员数量
     */
    @Schema(title = "群成员数量")
    private Short quantityOfMember = 0;

    /**
     * 限制人数
     */
    @Schema(title = "限制人数")
    private Short limit = 500;

    /**
     * 群备注
     */
    @Schema(title = "群备注")
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

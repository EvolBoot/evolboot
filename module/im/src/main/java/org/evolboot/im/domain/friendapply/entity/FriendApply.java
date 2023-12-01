package org.evolboot.im.domain.friendapply.entity;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.data.jpa.convert.StringListConverter;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Table(name = "evoltb_im_friend_apply")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "好友申请")
public class FriendApply extends JpaAbstractEntity<Long> implements AggregateRoot<FriendApply> {

    @Id
    private Long id;

    /**
     * 被申请的用户
     */
    @Schema(title = "被申请的用户")
    private Long toUserId;

    /**
     * 申请用户
     */
    @Schema(title = "申请用户")
    private Long fromUserId;


    /**
     * 申请原因
     */
    @Schema(title = "申请原因")
    @Convert(converter = StringListConverter.class)
    private List<String> applyReason = Lists.newArrayList();


    /**
     * 状态
     */
    @Schema(title = "状态")
    private FriendApplyState state = FriendApplyState.PENDING;

    /**
     * 未处理到期时间
     */
    @Schema(title = "未处理到期时间")
    private Date expireAt;

    /**
     * 处理时间
     */
    @Schema(title = "处理时间")
    private Date handleAt;

    /**
     * 会话ID
     */
    @Schema(title = "会话ID")
    private Long conversationId;


    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public FriendApply(Long toUserId, Long fromUserId) {
        generateId();
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
    }

    public void addApplyReason(String applyReason, Date expireAt) {
        this.applyReason.add(applyReason);
        this.expireAt = expireAt;
    }

    private void setState(FriendApplyState state) {
        this.state = state;
    }

    public void agree(Long conversationId) {
        Assert.isTrue(FriendApplyState.PENDING.equals(getState()), "已经处理过这个申请");
        setState(FriendApplyState.AGREE);
        this.handleAt = new Date();
        this.conversationId = conversationId;
    }

    public void refuse() {
        Assert.isTrue(FriendApplyState.PENDING.equals(getState()), "已经处理过这个申请");
        setState(FriendApplyState.REFUSE);
        this.handleAt = new Date();
    }

    public void autoAgree(Long conversationId) {
        Assert.isTrue(FriendApplyState.PENDING.equals(getState()), "已经处理过这个申请");
        setState(FriendApplyState.AUTO_AGREE);
        this.handleAt = new Date();
        this.conversationId = conversationId;
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public FriendApply root() {
        return this;
    }
}

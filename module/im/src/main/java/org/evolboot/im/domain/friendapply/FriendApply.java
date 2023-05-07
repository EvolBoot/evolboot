package org.evolboot.im.domain.friendapply;

import com.google.common.collect.Lists;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.data.jpa.convert.StringListConverter;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Table(name = "evol_im_friend_apply")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class FriendApply extends JpaAbstractEntity<Long> implements AggregateRoot<FriendApply> {

    @Id
    private Long id;

    /**
     * 被申请的用户
     */
    private Long toUserId;

    /**
     * 申请用户
     */
    private Long fromUserId;


    /**
     * 申请原因
     */
    @Convert(converter = StringListConverter.class)
    private List<String> applyReason = Lists.newArrayList();


    /**
     * 状态
     */
    private FriendApplyStatus status = FriendApplyStatus.PENDING;

    /**
     * 未处理到期时间
     */
    private Date expireAt;

    /**
     * 处理时间
     */
    private Date handleAt;


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

     private void setStatus(FriendApplyStatus status) {
        this.status = status;
    }

    public void agree() {
        Assert.isTrue(FriendApplyStatus.PENDING.equals(getStatus()),"已经处理过这个申请");
        setStatus(FriendApplyStatus.AGREE);
        this.handleAt = new Date();
    }

    public void refuse() {
        Assert.isTrue(FriendApplyStatus.PENDING.equals(getStatus()),"已经处理过这个申请");
        setStatus(FriendApplyStatus.REFUSE);
        this.handleAt = new Date();
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

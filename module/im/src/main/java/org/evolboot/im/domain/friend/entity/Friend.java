package org.evolboot.im.domain.friend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Table(name = "evol_im_friend")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Friend extends JpaAbstractEntity<Long> implements AggregateRoot<Friend> {

    @Id
    private Long id;

    /**
     * 所有者ID
     */
    private Long ownerUserId;

    /**
     * 好友ID
     */
    private Long friendUserId;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 状态
     */
    private FriendStatus status = FriendStatus.NORMAL;


    /**
     * 昵称备注
     */
    private String nicknameRemark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public Friend(Long ownerUserId, Long friendUserId, Long conversationId) {
        generateId();
        this.ownerUserId = ownerUserId;
        this.friendUserId = friendUserId;
        this.conversationId = conversationId;
    }


    public void joinBlacklist() {
        Assert.isTrue(FriendStatus.NORMAL.equals(this.getStatus()), "已经在你黑名单中了");
        this.status = FriendStatus.BLACKLIST;
    }

    public void removeBlacklist() {
        Assert.isTrue(FriendStatus.BLACKLIST.equals(this.getStatus()), "已经不在黑名单中了");
        this.status = FriendStatus.NORMAL;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Friend root() {
        return this;
    }
}

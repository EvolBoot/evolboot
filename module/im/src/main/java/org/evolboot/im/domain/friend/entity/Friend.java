package org.evolboot.im.domain.friend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.Assert;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Table(name = "evoltb_im_friend")
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
    private FriendState state = FriendState.NORMAL;


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
        Assert.isTrue(FriendState.NORMAL.equals(this.getState()), "已经在你黑名单中了");
        this.state = FriendState.BLACKLIST;
    }

    public void removeBlacklist() {
        Assert.isTrue(FriendState.BLACKLIST.equals(this.getState()), "已经不在黑名单中了");
        this.state = FriendState.NORMAL;
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

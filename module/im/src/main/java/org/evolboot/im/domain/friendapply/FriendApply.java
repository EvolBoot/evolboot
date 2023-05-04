package org.evolboot.im.domain.friendapply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.friendapply.repository.jpa.convert.FriendApplyLocaleListConverter;
import org.evolboot.core.domain.LocaleDomainPart;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
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
    private Long ownerUserId;

    /**
     * 申请用户
     */
    private Long applyUserId;


    /**
     * 申请原因
     */
    private String applyReason;

    /**
     * 状态
     */
    private FriendApplyStatus status;

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

    public FriendApply(Long ownerUserId, Long applyUserId, String applyReason, FriendApplyStatus status, Date expireAt) {
        generateId();
        this.ownerUserId = ownerUserId;
        this.applyUserId = applyUserId;
        this.applyReason = applyReason;
        this.status = status;
        this.expireAt = expireAt;
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

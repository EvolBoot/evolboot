package org.evolboot.im.domain.friend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.friend.repository.jpa.convert.FriendLocaleListConverter;
import org.evolboot.core.domain.LocaleDomainPart;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.List;


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

    public Friend(String name) {
        //   setLocales(locales);
        generateId();
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

package org.evolboot.im.domain.friend.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
public abstract class FriendSupportService {

    protected final FriendRepository repository;

    protected FriendSupportService(FriendRepository repository) {
        this.repository = repository;
    }

    public Friend findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.Friend.NOT_FOUND)));
    }

    public void userExistIsTrue(Long userId) {
        repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.Friend.NOT_FOUND)));
    }

    public Friend findByOwnerUserIdAndFriendUserId(Long ownerUserId, Long friendUserId) {
        return repository.findByOwnerUserIdAndFriendUserId(ownerUserId, friendUserId).orElseThrow(() -> new DomainNotFoundException("对方不是你的好友"));
    }

    /**
     * 会话ID构建
     *
     * @param user1 用户1
     * @param user2 用户2
     * @return
     */
    public String buildConversationId(Long user1, Long user2) {
        if (user1 > user2) {
            return user1 + "_" + user2;
        }
        return user2 + "_" + user1;
    }

}

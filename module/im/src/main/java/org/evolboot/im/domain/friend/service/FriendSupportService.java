package org.evolboot.im.domain.friend.service;

import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import lombok.extern.slf4j.Slf4j;

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

}

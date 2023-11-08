package org.evolboot.im.domain.friend.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.shared.event.im.FriendDeleteEvent;
import org.springframework.stereotype.Service;

/**
 * 删除好友
 *
 * @author evol
 */
@Service
@Slf4j
public class FriendDeleteService  {


    private final FriendSupportService supportService;

    private final FriendRepository repository;

    private final EventPublisher eventPublisher;

    protected FriendDeleteService(FriendRepository repository, FriendSupportService supportService, EventPublisher eventPublisher) {
        this.repository = repository;
        this.supportService = supportService;
        this.eventPublisher = eventPublisher;
    }

    public void deleteByFriendUserId(Long ownerUserId, Long friendUserId) {
        //TODO 国际化
        Friend friend = repository.findByOwnerUserIdAndFriendUserId(ownerUserId, friendUserId).orElseThrow(() -> new DomainNotFoundException("对方不是你的好友"));
        repository.deleteById(friend.id());
        eventPublisher.publishEvent(new FriendDeleteEvent(friend.id(), friend.getOwnerUserId(), friend.getFriendUserId(), friend.getConversationId()));

    }
}

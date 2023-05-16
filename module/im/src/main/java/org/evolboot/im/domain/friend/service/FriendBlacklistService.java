package org.evolboot.im.domain.friend.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.shared.event.im.FriendJoinBlacklistEvent;
import org.evolboot.shared.event.im.FriendRemoveBlacklistEvent;
import org.springframework.stereotype.Service;

/**
 * 删除好友
 *
 * @author evol
 */
@Service
@Slf4j
public class FriendBlacklistService extends FriendSupportService {

    private final EventPublisher eventPublisher;

    protected FriendBlacklistService(FriendRepository repository, EventPublisher eventPublisher) {
        super(repository);
        this.eventPublisher = eventPublisher;
    }

    public void joinBlacklist(Long ownerUserId, Long friendUserId) {
        //TODO 国际化
        Friend friend = findByOwnerUserIdAndFriendUserId(ownerUserId, friendUserId);
        friend.joinBlacklist();
        repository.save(friend);
        eventPublisher.publishEvent(new FriendJoinBlacklistEvent(friend.id(), friend.getOwnerUserId(), friend.getFriendUserId(), friend.getConversationId()));
    }

    public void removeBlacklist(Long ownerUserId, Long friendUserId) {
        //TODO 国际化
        Friend friend = findByOwnerUserIdAndFriendUserId(ownerUserId, friendUserId);
        friend.removeBlacklist();
        repository.save(friend);
        eventPublisher.publishEvent(new FriendRemoveBlacklistEvent(friend.id(), friend.getOwnerUserId(), friend.getFriendUserId(), friend.getConversationId()));
    }
}

package org.evolboot.im.domain.friend.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainException;
import org.evolboot.core.util.Assert;
import org.evolboot.im.acl.client.UserClient;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendStatus;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author evol
 */
@Service
@Slf4j
public class AllowAddFriendService extends FriendSupportService {

    private final UserClient userClient;

    protected AllowAddFriendService(FriendRepository repository, UserClient userClient) {
        super(repository);
        this.userClient = userClient;
    }

    public void execute(Long ownerUserId, Long friendUserId) {

        boolean allowed = userClient.allowAddFriend(ownerUserId);
        Assert.isTrue(allowed, "该用户不存在");

        Optional<Friend> friendOptional = repository.findByOwnerUserIdAndFriendUserId(ownerUserId, friendUserId);
        if (friendOptional.isEmpty()) {
            return;
        }
        Friend friend = friendOptional.get();
        if (FriendStatus.BLOCK.equals(friend.getStatus())) {
            log.info("Friend:AllowAddFriendService:{},{},拉黑了关系,不允许添加好友", ownerUserId, friend);
            //TODO 多语言
            throw new DomainException("限制了添加好友关系");
        }
        if (FriendStatus.NORMAL.equals(friend.getStatus())) {
            log.info("Friend:AllowAddFriendService:{},{},已经是好友了,不允许添加好友", ownerUserId, friend);
            //TODO 多语言
            throw new DomainException("你们已经是好友了");
        }

    }
}

package org.evolboot.im.domain.friend.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ErrorCodeException;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.acl.client.UserClient;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.entity.FriendState;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.userconversation.UserConversationAppService;
import org.springframework.stereotype.Service;

import static org.evolboot.im.ImI18nMessage.Friend.USER_NOT_FOUND;
import static org.evolboot.im.ImI18nMessage.Friend.YOU_CANNOT_ADD_YOURSELF_AS_A_FRIEND;


/**
 * 添加好友时：
 * 1. 判断已经是正常好友，是，则返回提示信息
 * 2. 判断已被我拉黑，是，发出提示
 * 3. 判断已被对方拉黑，是，发出提示
 * 4. 判断是否单方面删除过对方（对方有我的好友关系，但我没有），则直接添加（启用会话）
 * 5. 发出好友申请
 * TODO 多语言
 *
 * @author evol
 */
@Service
@Slf4j
public class FriendApplyService {


    private final FriendSupportService supportService;

    private final FriendRepository repository;

    private final FriendCreateFactory factory;
    private final UserClient userClient;

    private final UserConversationAppService userConversationAppService;

    protected FriendApplyService(FriendRepository repository, FriendSupportService supportService, FriendCreateFactory factory, UserClient userClient, UserConversationAppService userConversationAppService) {
        this.repository = repository;
        this.supportService = supportService;
        this.factory = factory;
        this.userClient = userClient;
        this.userConversationAppService = userConversationAppService;
    }

    public Friend execute(Request request) {

        Assert.isTrue(userClient.existsByUserId(request.getFriendUserId()), USER_NOT_FOUND);
        Assert.isTrue(!request.getOwnerUserId().equals(request.getFriendUserId()), YOU_CANNOT_ADD_YOURSELF_AS_A_FRIEND);

        Friend owner = repository.findByOwnerUserIdAndFriendUserId(request.getOwnerUserId(), request.getFriendUserId()).orElse(null);
        Friend friend = repository.findByOwnerUserIdAndFriendUserId(request.getFriendUserId(), request.getOwnerUserId()).orElse(null);
        // 1.判断已经是正常好友，是，则返回提示信息
        if (ExtendObjects.nonNull(owner) && ExtendObjects.nonNull(friend) && FriendState.NORMAL.equals(owner.getState()) && FriendState.NORMAL.equals(friend.getState())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.haveBeenFriends());
        }
        // 2.判断已被我拉黑，是，发出提示
        if (ExtendObjects.nonNull(owner) && FriendState.BLACKLIST.equals(owner.getState())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.alreadyOnYourBlacklist());
        }
        // 3.判断已被对方拉黑，是，发出提示
        if (ExtendObjects.nonNull(friend) && FriendState.BLACKLIST.equals(friend.getState())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.hasBeenBlockedByFriends());
        }
        // 4.判断是否单方面删除过对方（对方有我的好友关系，但我没有），则直接添加（启用会话）
        if (ExtendObjects.isNull(owner) && ExtendObjects.nonNull(friend)) {
            owner = factory.execute(new FriendCreateFactory.Request(request.getOwnerUserId(), request.getFriendUserId()));
            return owner;
        }
        return null;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private Long ownerUserId;
        private Long friendUserId;
    }
}

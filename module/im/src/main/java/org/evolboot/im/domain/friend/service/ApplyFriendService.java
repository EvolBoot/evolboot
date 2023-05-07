package org.evolboot.im.domain.friend.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ErrorCodeException;
import org.evolboot.core.lang.BusinessResult;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.ImSubCode;
import org.evolboot.im.acl.client.UserClient;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendStatus;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.springframework.stereotype.Service;

import static org.evolboot.im.ImI18nMessage.Friend.*;
import static org.evolboot.im.ImSubCode.*;


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
public class ApplyFriendService extends FriendSupportService {

    private final FriendCreateFactory factory;
    private final UserClient userClient;
    private final FriendApplyAppService friendApplyAppService;

    protected ApplyFriendService(FriendRepository repository, FriendCreateFactory factory, UserClient userClient, FriendApplyAppService friendApplyAppService) {
        super(repository);
        this.factory = factory;
        this.userClient = userClient;
        this.friendApplyAppService = friendApplyAppService;
    }

    public BusinessResult<Object> execute(Request request) {

        Assert.isTrue(userClient.existsByUserId(request.getFriendUserId()), USER_NOT_FOUND);
        Assert.isTrue(!request.getOwnerUserId().equals(request.getFriendUserId()), YOU_CANNOT_ADD_YOURSELF_AS_A_FRIEND);

        Friend owner = repository.findByOwnerUserIdAndFriendUserId(request.getOwnerUserId(), request.getFriendUserId()).orElse(null);
        Friend friend = repository.findByOwnerUserIdAndFriendUserId(request.getFriendUserId(), request.getOwnerUserId()).orElse(null);
        // 1.判断已经是正常好友，是，则返回提示信息
        if (ExtendObjects.nonNull(owner) && ExtendObjects.nonNull(friend) && FriendStatus.NORMAL.equals(owner.getStatus()) && FriendStatus.NORMAL.equals(friend.getStatus())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.haveBeenFriends());
        }
        // 2.判断已被我拉黑，是，发出提示
        if (ExtendObjects.nonNull(owner) && FriendStatus.BLOCK.equals(owner.getStatus())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.alreadyOnYourBlacklist());
        }
        // 3.判断已被对方拉黑，是，发出提示
        if (ExtendObjects.nonNull(friend) && FriendStatus.BLOCK.equals(friend.getStatus())) {
            throw ErrorCodeException.of(ImI18nMessage.Friend.hasBeenBlockedByFriends());
        }
        // 4.判断是否单方面删除过对方（对方有我的好友关系，但我没有），则直接添加（启用会话）
        if (ExtendObjects.isNull(owner) && ExtendObjects.nonNull(friend)) {
            owner = factory.execute(new FriendCreateFactory.Request(request.getOwnerUserId(), request.getFriendUserId()));
            return new BusinessResult<>(SUCCEEDED_IN_ADDING_A_FRIEND, "添加好友成功", owner);
        }
        // 5.发出好友申请
        friendApplyAppService.create(new FriendApplyCreateFactory.Request(
                // to my friend
                request.getFriendUserId(),
                // from me
                request.getOwnerUserId(),
                request.getApplyReason()
        ));
        return new BusinessResult<>(SENT_FRIENDS_REQUEST, "已发送好友请求");
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private Long ownerUserId;
        private Long friendUserId;
        private String applyReason;
    }
}

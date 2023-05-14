package org.evolboot.im.domain.friendapply.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendAppService;
import org.evolboot.im.domain.friend.service.FriendApplyService;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import lombok.extern.slf4j.Slf4j;

/**
 * 好友申请
 * TODO 多语言
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyCreateFactory extends FriendApplySupportService {

    /**
     * 7天处理时间
     */
    private final static int EXPIRE_MAX_OF_DAY = 7;

    /**
     * 最大申请次数
     */
    private final static int MAXIMUM_OF_APPLY = 10;

    private final FriendAppService friendAppService;

    protected FriendApplyCreateFactory(FriendApplyRepository repository, FriendAppService friendAppService) {
        super(repository);
        this.friendAppService = friendAppService;
    }

    public FriendApply execute(Request request) {
        Friend ownerFriend = friendAppService.apply(new FriendApplyService.Request(request.getFromUserId(), request.getToUserId()));
        FriendApply friendApply = repository.findByToUserIdAndFromUserIdAndStatus(request.getToUserId(), request.getFromUserId(), FriendApplyStatus.PENDING).
                orElseGet(() -> new FriendApply(request.getToUserId(), request.getFromUserId()));
        if (ExtendObjects.isNull(ownerFriend)) {
            Assert.isTrue(friendApply.getApplyReason().size() <= MAXIMUM_OF_APPLY, "已经发出申请了,请等待对方审核");
            friendApply.addApplyReason(request.getApplyReason(), ExtendDateUtil.offsetDay(EXPIRE_MAX_OF_DAY));
        } else {
            friendApply.autoAgree(ownerFriend.getConversationId());
        }
        repository.save(friendApply);
        return friendApply;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        /**
         * 被申请的用户
         */
        private Long toUserId;

        /**
         * 申请用户
         */
        private Long fromUserId;

        /**
         * 申请原因
         */
        private String applyReason;
    }

}

package org.evolboot.im.domain.friendapply.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.im.domain.friend.FriendAppService;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyCreateFactory extends FriendApplySupportService {

    private final FriendAppService friendAppService;

    /**
     * 7天处理时间
     */
    private final static Integer EXPIRE_MAX_OF_DAY = 7;

    protected FriendApplyCreateFactory(FriendApplyRepository repository, FriendAppService friendAppService) {
        super(repository);
        this.friendAppService = friendAppService;
    }

    public FriendApply execute(Request request) {
        Assert.isTrue(!request.getOwnerUserId().equals(request.getApplyUserId()),"不能添加自己为好友");
        Optional<FriendApply> friendApplyOptional = repository.findByOwnerUserIdAndApplyUserIdAndStatus(request.getOwnerUserId(), request.getApplyUserId(), FriendApplyStatus.PENDING);
        //TODO 多语言
        Assert.isTrue(friendApplyOptional.isEmpty(), "已经申请过好友关系了,请等待通过");
        friendAppService.allowAddFriend(request.getOwnerUserId(), request.getApplyUserId());
        FriendApply friendApply = new FriendApply(request.getOwnerUserId(), request.getApplyUserId(), request.getApplyReason(), FriendApplyStatus.PENDING, ExtendDateUtil.offsetDay(EXPIRE_MAX_OF_DAY));
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
        private Long ownerUserId;

        /**
         * 申请用户
         */
        private Long applyUserId;

        /**
         * 申请原因
         */
        private String applyReason;
    }

}

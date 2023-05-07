package org.evolboot.im.domain.friendapply.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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

    protected FriendApplyCreateFactory(FriendApplyRepository repository) {
        super(repository);
    }

    public FriendApply execute(Request request) {
        FriendApply friendApply = repository.findByToUserIdAndFromUserIdAndStatus(request.getToUserId(), request.getFromUserId(), FriendApplyStatus.PENDING).
                orElseGet(() -> new FriendApply(request.getToUserId(), request.getFromUserId()));
        Assert.isTrue(friendApply.getApplyReason().size() <= MAXIMUM_OF_APPLY, "已经发出申请了,请等待对方审核");
        friendApply.addApplyReason(request.getApplyReason(), ExtendDateUtil.offsetDay(EXPIRE_MAX_OF_DAY));
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

package org.evolboot.im.domain.friendapply.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendIllegalArgumentException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.FriendAppService;
import org.evolboot.im.domain.friend.service.FriendCreateFactory;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.entity.FriendApplyState;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.shared.ApplyAuditOperation;
import org.springframework.stereotype.Service;

/**
 * 好友申请
 * spriTODO 多语言
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@Service
public class FriendApplyAuditService {

    private final FriendApplySupportService supportService;

    private final FriendApplyRepository repository;

    private final FriendAppService friendAppService;

    protected FriendApplyAuditService(FriendApplyRepository repository, FriendAppService friendAppService, FriendApplySupportService supportService) {
        this.supportService = supportService;
        this.repository = repository;
        this.friendAppService = friendAppService;
    }

    public FriendApply execute(Request request) {
        FriendApply friendApply = supportService.findById(request.getId());
        Assert.isTrue(friendApply.getToUserId().equals(request.getToUserId()), I18NMessageHolder.message(ImI18nMessage.FriendApply.NOT_FOUND));
        if (FriendApplyState.EXPIRE.equals(friendApply.getState())) {
            throw new ExtendIllegalArgumentException("该申请已经过期");
        }
        Assert.isTrue(FriendApplyState.PENDING.equals(friendApply.getState()), "已经处理过这个申请");
        if (ApplyAuditOperation.AGREE.equals(request.getOperation())) {
            // 创建好友关系
            Friend friend = friendAppService.create(new FriendCreateFactory.Request(friendApply.getToUserId(), friendApply.getFromUserId()));
            friendApply.agree(friend.getConversationId());
        } else {
            friendApply.refuse();
        }
        repository.save(friendApply);
        return friendApply;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private Long id;

        private Long toUserId;

        public ApplyAuditOperation operation;

    }

}

package org.evolboot.im.domain.friend.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;
import org.evolboot.im.domain.friendapply.service.FriendApplyAuditService;
import org.evolboot.im.domain.shared.ApplyAuditOperation;
import org.springframework.stereotype.Service;

/**
 * 申请审核
 *
 * @author evol
 */
@Service
@Slf4j
public class ApplyAuditService extends FriendSupportService {

    private final FriendApplyAppService friendApplyAppService;
    private final FriendCreateFactory factory;

    protected ApplyAuditService(FriendRepository repository, FriendApplyAppService friendApplyAppService, FriendCreateFactory factory) {
        super(repository);
        this.friendApplyAppService = friendApplyAppService;
        this.factory = factory;
    }

    public Friend execute(Request request) {
        FriendApply friendApply = friendApplyAppService.audit(new FriendApplyAuditService.Request(request.getApplyId(), request.getOwnerUserId(), request.getOperation()));
        if (FriendApplyStatus.AGREE.equals(friendApply.getStatus())) {
            return factory.execute(new FriendCreateFactory.Request(friendApply.getToUserId(), friendApply.getFromUserId()));
        }
        return null;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {

        private Long ownerUserId;

        private Long applyId;

        private ApplyAuditOperation operation;

    }

}

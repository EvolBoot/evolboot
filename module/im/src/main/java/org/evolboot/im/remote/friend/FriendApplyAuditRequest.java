package org.evolboot.im.remote.friend;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.evolboot.im.domain.friend.service.ApplyAuditService;
import org.evolboot.im.domain.friend.service.ApplyFriendService;
import org.evolboot.im.domain.shared.ApplyAuditOperation;

/**
 * @author evol
 */
@Getter
@Setter
public class FriendApplyAuditRequest {


    private Long applyId;

    private ApplyAuditOperation operation;

    public ApplyAuditService.Request to(Long ownerUserId) {
        return new ApplyAuditService.Request(ownerUserId, applyId, operation);
    }
}

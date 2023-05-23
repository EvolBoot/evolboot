package org.evolboot.im.remote.friendapply.dto;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.friendapply.service.FriendApplyAuditService;
import org.evolboot.im.domain.shared.ApplyAuditOperation;

/**
 * @author evol
 */
@Getter
@Setter
public class FriendApplyAuditRequest {


    private Long id;

    private ApplyAuditOperation operation;

    public FriendApplyAuditService.Request to(Long toUserId) {
        return new FriendApplyAuditService.Request(
                id, toUserId, operation
        );
    }

}

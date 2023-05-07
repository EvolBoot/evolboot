package org.evolboot.im.remote.friend;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.friend.service.ApplyFriendService;

/**
 * @author evol
 */
@Getter
@Setter
public class FriendApplyCreateRequest {

    private Long friendUserId;
    private String applyReason;

    public ApplyFriendService.Request to(Long ownerUserId) {
        return new ApplyFriendService.Request(
                ownerUserId, friendUserId, applyReason
        );
    }
}

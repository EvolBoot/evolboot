package org.evolboot.im.remote.friendapply.dto;


import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */

@Setter
@Getter
public class FriendApplyCreateRequest {

    /**
     * 被申请的用户
     */
    private Long userId;

    /**
     * 申请原因
     */
    private String applyReason;

    public FriendApplyCreateFactory.Request to(Long fromUserId) {
        return new FriendApplyCreateFactory.Request(
                userId, fromUserId, applyReason
        );
    }


}

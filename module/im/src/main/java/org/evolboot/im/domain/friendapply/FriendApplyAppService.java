package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.service.FriendApplyAuditService;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplyQuery;

import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public interface FriendApplyAppService {

    FriendApply create(FriendApplyCreateFactory.Request request);

    FriendApply audit(FriendApplyAuditService.Request request);

    void delete(Long id);


}

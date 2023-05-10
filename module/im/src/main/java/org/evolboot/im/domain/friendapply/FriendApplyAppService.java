package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.service.FriendApplyAuditService;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplyUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public interface FriendApplyAppService {

    FriendApply findById(Long id);

    FriendApply create(FriendApplyCreateFactory.Request request);

    FriendApply audit(FriendApplyAuditService.Request request);

    void delete(Long id);

    List<FriendApply> findAll();

    List<FriendApply> findAll(FriendApplyQuery query);

    Page<FriendApply> page(FriendApplyQuery query);



    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<FriendApply> findOne(FriendApplyQuery query);



}

package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.friendapply.service.FriendApplyCreateFactory;
import org.evolboot.im.domain.friendapply.service.FriendApplyUpdateService;

import java.util.List;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public interface FriendApplyAppService {

    FriendApply findById(Long id);

    FriendApply create(FriendApplyCreateFactory.Request request);

    void update(Long id, FriendApplyUpdateService.Request request);

    void delete(Long id);

    List<FriendApply> findAll();

    List<FriendApply> findAll(FriendApplyQuery query);

    Page<FriendApply> page(FriendApplyQuery query);



}

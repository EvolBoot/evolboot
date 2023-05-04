package org.evolboot.im.domain.friendapply.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyQuery;
import org.evolboot.im.domain.friendapply.FriendApplyStatus;


import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public interface FriendApplyRepository {

    FriendApply save(FriendApply friendApply);

    Optional<FriendApply> findById(Long id);

    Page<FriendApply> page(FriendApplyQuery query);

    void deleteById(Long id);

    List<FriendApply> findAll();

    List<FriendApply> findAll(FriendApplyQuery query);

    Optional<FriendApply> findByOwnerUserIdAndApplyUserIdAndStatus(Long ownerUserId, Long applyUserId, FriendApplyStatus status);

}

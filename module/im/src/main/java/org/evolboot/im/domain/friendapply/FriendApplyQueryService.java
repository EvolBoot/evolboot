package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.dto.FriendApplyQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 好友申请
 *
 * @author evol
 * @date 2023-06-14 20:01:33
 */
public interface FriendApplyQueryService {

    FriendApply findById(Long id);

    List<FriendApply> findAll();

    List<FriendApply> findAll(FriendApplyQueryRequest query);

    Page<FriendApply> page(FriendApplyQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<FriendApply> findOne(FriendApplyQueryRequest query);


}

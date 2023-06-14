package org.evolboot.im.domain.friend;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.service.FriendQuery;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 好友关系
 *
 * @author evol
 * @date 2023-06-14 19:58:06
 */
public interface FriendQueryService {

    Friend findById(Long id);

    List<Friend> findAll();

    List<Friend> findAll(FriendQuery query);

    Page<Friend> page(FriendQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Friend> findOne(FriendQuery query);


}

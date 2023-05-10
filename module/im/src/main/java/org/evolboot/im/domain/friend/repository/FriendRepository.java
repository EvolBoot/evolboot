package org.evolboot.im.domain.friend.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendQuery;


import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
public interface FriendRepository {

    Friend save(Friend friend);

    Optional<Friend> findById(Long id);

    Page<Friend> page(FriendQuery query);

    void deleteById(Long id);

    List<Friend> findAll();

    List<Friend> findAll(FriendQuery query);

    Optional<Friend> findByOwnerUserIdAndFriendUserId(Long ownerUserId, Long friendUserId);


    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Friend> findOne(FriendQuery query);

}

package org.evolboot.im.domain.friend.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.friend.Friend;

import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
public interface FriendRepository extends BaseRepository<Friend, Long> {


    void deleteById(Long id);

    Optional<Friend> findByOwnerUserIdAndFriendUserId(Long ownerUserId, Long friendUserId);


    List<Friend> findAll();

    Friend save(Friend friend);

    Optional<Friend> findById(Long aLong);

    <Q extends Query> List<Friend> findAll(Q query);

    <Q extends Query> Optional<Friend> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Friend> page(Q query);
}

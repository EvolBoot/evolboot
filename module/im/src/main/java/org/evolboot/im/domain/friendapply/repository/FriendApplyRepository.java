package org.evolboot.im.domain.friendapply.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.entity.FriendApplyState;

import java.util.List;
import java.util.Optional;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
public interface FriendApplyRepository extends BaseRepository<FriendApply, Long> {

    FriendApply save(FriendApply friendApply);

    Optional<FriendApply> findById(Long id);


    void deleteById(Long id);

    List<FriendApply> findAll();


    Optional<FriendApply> findByToUserIdAndFromUserIdAndState(Long toUserId, Long fromUserId, FriendApplyState state);


    <Q extends Query> List<FriendApply> findAll(Q query);

    <Q extends Query> Optional<FriendApply> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<FriendApply> page(Q query);

}

package org.evolboot.im.domain.friend;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friend.entity.Friend;
import org.evolboot.im.domain.friend.service.FriendApplyService;
import org.evolboot.im.domain.friend.service.FriendCreateFactory;
import org.evolboot.im.domain.friend.service.FriendQuery;
import org.evolboot.im.domain.friend.service.FriendUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
public interface FriendAppService  {

    Friend findById(Long id);

    Friend create(FriendCreateFactory.Request request);

    void update( FriendUpdateService.Request request);

    void delete(Long id);

    List<Friend> findAll();

    List<Friend> findAll(FriendQuery query);

    Page<Friend> page(FriendQuery query);


    /**
     * 添加好友请求
     *
     * @param request
     * @return
     */
    Friend apply(FriendApplyService.Request request);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Friend> findOne(FriendQuery query);

    /**
     * 加入黑名单
     */
    void joinBlacklist(Long ownerUserId, Long friendUserId);


    /**
     * 移出黑名单
     */
    void removeBlacklist(Long ownerUserId, Long friendUserId);


    /**
     * 删除好友
     */
    void deleteByFriendUserId(Long ownerUserId, Long friendUserId);


}

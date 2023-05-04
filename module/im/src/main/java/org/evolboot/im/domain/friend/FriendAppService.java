package org.evolboot.im.domain.friend;

import org.evolboot.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.friend.service.FriendCreateFactory;
import org.evolboot.im.domain.friend.service.FriendUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
public interface FriendAppService {

    Friend findById(Long id);

    Friend create(FriendCreateFactory.Request request);

    void update(Long id, FriendUpdateService.Request request);

    void delete(Long id);

    List<Friend> findAll();

    List<Friend> findAll(FriendQuery query);

    Page<Friend> page(FriendQuery query);

    void allowAddFriend(Long ownerUserId, Long friendUserId);
}

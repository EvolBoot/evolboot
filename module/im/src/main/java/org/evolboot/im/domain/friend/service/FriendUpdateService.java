package org.evolboot.im.domain.friend.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendRequestBase;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.springframework.stereotype.Service;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@Service
public class FriendUpdateService extends FriendSupportService {
    protected FriendUpdateService(FriendRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Friend friend = findById(id);
        repository.save(friend);
    }

    public static class Request extends FriendRequestBase {
    }

}

package org.evolboot.im.domain.friend.service;


import org.springframework.stereotype.Service;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.
        FriendRequestBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@Service
public class FriendCreateFactory extends FriendSupportService {
    protected FriendCreateFactory(FriendRepository repository) {
        super(repository);
    }

    public Friend execute(Request request) {
        Friend friend = new Friend("test");
        repository.save(friend);
        return friend;
    }

    public static class Request extends FriendRequestBase {

    }

}

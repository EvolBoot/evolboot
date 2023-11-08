package org.evolboot.im.domain.friend.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friend.entity.Friend;
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
public class FriendUpdateService {

    private final FriendRepository repository;
    private final FriendSupportService supportService;

    protected FriendUpdateService(FriendRepository repository, FriendSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Friend friend = supportService.findById(request.getId());
        repository.save(friend);
    }

    @Getter
    @Setter
    public static class Request extends FriendRequestBase {
        private Long id;
    }

}

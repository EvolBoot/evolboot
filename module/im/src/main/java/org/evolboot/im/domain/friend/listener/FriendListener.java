package org.evolboot.im.domain.friend.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friend.repository.FriendRepository;
import org.evolboot.im.domain.friend.service.FriendSupportService;
import org.springframework.stereotype.Service;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Service
@Slf4j
public class FriendListener {

    private final FriendRepository repository;

    private final FriendSupportService supportService;

    protected FriendListener(FriendRepository repository, FriendSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

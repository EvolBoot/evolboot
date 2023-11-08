package org.evolboot.im.domain.friendapply.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;
import org.springframework.stereotype.Service;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Service
@Slf4j
public class FriendApplyListener {

    private final FriendApplyRepository repository;

    private final FriendApplySupportService supportService;

    protected FriendApplyListener(FriendApplyRepository repository, FriendApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

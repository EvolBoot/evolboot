package org.evolboot.im.domain.friendapply;

import org.evolboot.im.domain.friendapply.repository.FriendApplyRepository;
import org.evolboot.im.domain.friendapply.service.FriendApplySupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Service
@Slf4j
public class FriendApplyListener extends FriendApplySupportService {

    protected FriendApplyListener(FriendApplyRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

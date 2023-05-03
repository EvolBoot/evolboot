package org.evolboot.im.domain.group;

import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.service.GroupSupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Service
@Slf4j
public class GroupListener extends GroupSupportService {

    protected GroupListener(GroupRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

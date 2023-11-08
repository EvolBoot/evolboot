package org.evolboot.im.domain.group.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.service.GroupSupportService;
import org.springframework.stereotype.Service;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Service
@Slf4j
public class GroupListener {

    private final GroupRepository repository;

    private final GroupSupportService supportService;

    protected GroupListener(GroupRepository repository, GroupSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

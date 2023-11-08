package org.evolboot.im.domain.groupapply.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;
import org.evolboot.im.domain.groupapply.service.GroupApplySupportService;
import org.springframework.stereotype.Service;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Service
@Slf4j
public class GroupApplyListener {

    private final GroupApplyRepository repository;

    private final GroupApplySupportService supportService;

    protected GroupApplyListener(GroupApplyRepository repository, GroupApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

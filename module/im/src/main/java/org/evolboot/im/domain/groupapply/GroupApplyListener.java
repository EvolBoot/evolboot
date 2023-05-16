package org.evolboot.im.domain.groupapply;

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
public class GroupApplyListener extends GroupApplySupportService {

    protected GroupApplyListener(GroupApplyRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

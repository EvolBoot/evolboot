package org.evolboot.im.domain.groupapply;

import org.evolboot.im.domain.groupapply.entity.GroupApply;
import org.evolboot.im.domain.groupapply.service.GroupApplyCreateFactory;
import org.evolboot.im.domain.groupapply.service.GroupApplyUpdateService;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
public interface GroupApplyAppService {


    GroupApply create(GroupApplyCreateFactory.Request request);

    void update(GroupApplyUpdateService.Request request);

    void delete(Long id);


}

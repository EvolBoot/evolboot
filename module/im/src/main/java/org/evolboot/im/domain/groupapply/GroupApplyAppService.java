package org.evolboot.im.domain.groupapply;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupapply.entity.GroupApply;
import org.evolboot.im.domain.groupapply.service.GroupApplyCreateFactory;
import org.evolboot.im.domain.groupapply.service.GroupApplyQuery;
import org.evolboot.im.domain.groupapply.service.GroupApplyUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
public interface GroupApplyAppService {

    GroupApply findById(Long id);

    GroupApply create(GroupApplyCreateFactory.Request request);

    void update(GroupApplyUpdateService.Request request);

    void delete(Long id);

    List<GroupApply> findAll();

    List<GroupApply> findAll(GroupApplyQuery query);

    Page<GroupApply> page(GroupApplyQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<GroupApply> findOne(GroupApplyQuery query);


}

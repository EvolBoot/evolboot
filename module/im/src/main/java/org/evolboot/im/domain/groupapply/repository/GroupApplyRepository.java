package org.evolboot.im.domain.groupapply.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApplyQuery;


import java.util.List;
import java.util.Optional;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
public interface GroupApplyRepository {

    GroupApply save(GroupApply groupApply);

    Optional<GroupApply> findById(Long id);

    Page<GroupApply> page(GroupApplyQuery query);

    void deleteById(Long id);

    List<GroupApply> findAll();

    List<GroupApply> findAll(GroupApplyQuery query);
}

package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.group.service.GroupCreateFactory;
import org.evolboot.im.domain.group.service.GroupUpdateService;

import java.util.List;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public interface GroupAppService {

    Group findById(Long id);

    Group create(GroupCreateFactory.Request request);

    void update(Long id, GroupUpdateService.Request request);

    void delete(Long id);

    List<Group> findAll();

    List<Group> findAll(GroupQuery query);

    Page<Group> page(GroupQuery query);


}

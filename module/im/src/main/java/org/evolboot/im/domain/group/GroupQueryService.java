package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.service.GroupQuery;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 群组
 *
 * @author evol
 * @date 2023-06-14 20:03:00
 */
public interface GroupQueryService {

    Group findById(Long id);

    List<Group> findAll();

    List<Group> findAll(GroupQuery query);

    Page<Group> page(GroupQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Group> findOne(GroupQuery query);


}

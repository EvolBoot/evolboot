package org.evolboot.im.domain.group.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.group.Group;
import org.evolboot.im.domain.group.Group;
import org.evolboot.im.domain.group.GroupQuery;


import java.util.List;
import java.util.Optional;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public interface GroupRepository {

    Group save(Group group);

    Optional<Group> findById(Long id);

    Page<Group> page(GroupQuery query);

    void deleteById(Long id);

    List<Group> findAll();

    List<Group> findAll(GroupQuery query);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Group> findOne(GroupQuery query);
}

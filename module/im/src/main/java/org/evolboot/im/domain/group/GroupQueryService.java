package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.dto.GroupQueryRequest;

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

    List<Group> findAll(GroupQueryRequest query);

    Page<Group> page(GroupQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Group> findOne(GroupQueryRequest query);


}

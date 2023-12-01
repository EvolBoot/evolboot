package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.dto.GroupMemberQueryRequest;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 群成员
 *
 * @author evol
 * @date 2023-06-14 20:05:56
 */
public interface GroupMemberQueryService {

    GroupMember findById(Long id);

    List<GroupMember> findAll();

    List<GroupMember> findAll(GroupMemberQueryRequest query);

    Page<GroupMember> page(GroupMemberQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<GroupMember> findOne(GroupMemberQueryRequest query);


}

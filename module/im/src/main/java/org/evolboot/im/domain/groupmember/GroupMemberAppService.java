package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.service.GroupMemberCreateFactory;
import org.evolboot.im.domain.groupmember.service.GroupMemberQuery;
import org.evolboot.im.domain.groupmember.service.GroupMemberUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
public interface GroupMemberAppService {

    GroupMember findById(Long id);

    GroupMember create(GroupMemberCreateFactory.Request request);

    /**
     * 禁言
     */


    /**
     * 解禁
     */

    /**
     * 踢除
     */


    void update(Long id, GroupMemberUpdateService.Request request);

    void delete(Long id);

    List<GroupMember> findAll();

    List<GroupMember> findAll(GroupMemberQuery query);

    Page<GroupMember> page(GroupMemberQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<GroupMember> findOne(GroupMemberQuery query);


}

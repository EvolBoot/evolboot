package org.evolboot.im.domain.groupmember;

import org.evolboot.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import org.evolboot.im.domain.groupmember.service.GroupMemberCreateFactory;
import org.evolboot.im.domain.groupmember.service.GroupMemberUpdateService;

import java.util.List;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
public interface GroupMemberAppService {

    GroupMember findById(Long id);

    GroupMember create(GroupMemberCreateFactory.Request request);

    void update(Long id, GroupMemberUpdateService.Request request);

    void delete(Long id);

    List<GroupMember> findAll();

    List<GroupMember> findAll(GroupMemberQuery query);

    Page<GroupMember> page(GroupMemberQuery query);


}

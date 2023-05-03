package org.evolboot.im.domain.groupmember.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberQuery;


import java.util.List;
import java.util.Optional;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
public interface GroupMemberRepository {

    GroupMember save(GroupMember groupMember);

    Optional<GroupMember> findById(Long id);

    Page<GroupMember> page(GroupMemberQuery query);

    void deleteById(Long id);

    List<GroupMember> findAll();

    List<GroupMember> findAll(GroupMemberQuery query);
}

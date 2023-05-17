package org.evolboot.im.domain.groupmember.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberQuery;

import java.util.List;
import java.util.Optional;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-16 17:06:45
 */
public interface GroupMemberRepository extends BaseRepository<GroupMember, Long> {

    GroupMember save(GroupMember groupMember);

    Optional<GroupMember> findById(Long id);

    void deleteById(Long id);

    List<GroupMember> findAll();

    <Q extends Query> List<GroupMember> findAll(Q query);

    <Q extends Query> Optional<GroupMember> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<GroupMember> page(Q query);

}

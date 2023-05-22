package org.evolboot.im.domain.group.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.group.entity.Group;

import java.util.List;
import java.util.Optional;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public interface GroupRepository extends BaseRepository<Group, Long> {

    Group save(Group group);

    Optional<Group> findById(Long id);

    void deleteById(Long id);

    List<Group> findAll();


    <Q extends Query> List<Group> findAll(Q query);

    <Q extends Query> Optional<Group> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Group> page(Q query);

}

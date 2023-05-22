package org.evolboot.im.domain.groupapply.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.im.domain.groupapply.entity.GroupApply;

import java.util.List;
import java.util.Optional;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
public interface GroupApplyRepository extends BaseRepository<GroupApply, Long> {

    GroupApply save(GroupApply groupApply);

    Optional<GroupApply> findById(Long id);


    void deleteById(Long id);

    List<GroupApply> findAll();


    <Q extends Query> List<GroupApply> findAll(Q query);

    <Q extends Query> Optional<GroupApply> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<GroupApply> page(Q query);

}

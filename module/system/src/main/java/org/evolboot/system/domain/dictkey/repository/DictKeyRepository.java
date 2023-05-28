package org.evolboot.system.domain.dictkey.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.dictkey.entity.DictKey;

import java.util.List;
import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
public interface DictKeyRepository extends BaseRepository<DictKey, Long> {

    DictKey save(DictKey dictKey);

    Optional<DictKey> findById(Long id);

    Optional<DictKey> findByKey(String key);

    void deleteAllByIdInBatch(Iterable<Long> ids);

    void deleteById(Long id);

    List<DictKey> findAll();


    <Q extends Query> List<DictKey> findAll(Q query);

    <Q extends Query> Optional<DictKey> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<DictKey> page(Q query);
}

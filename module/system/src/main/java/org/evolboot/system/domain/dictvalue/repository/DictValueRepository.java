package org.evolboot.system.domain.dictvalue.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.dictvalue.entity.DictValue;

import java.util.List;
import java.util.Optional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
public interface DictValueRepository extends BaseRepository<DictValue, Long> {

    DictValue save(DictValue dictValue);

    Optional<DictValue> findById(Long id);


    void deleteById(Long id);

    void deleteByDictKeyId(Long dictKeyId);

    List<DictValue> findByDictKeyId(Long dictKeyId);

    List<DictValue> findAll();


    <Q extends Query> List<DictValue> findAll(Q query);

    <Q extends Query> Optional<DictValue> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<DictValue> page(Q query);
}

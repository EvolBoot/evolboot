package org.evolboot.system.domain.dictvalue.repository;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.dictvalue.DictValue;
import org.evolboot.system.domain.dictvalue.DictValueQuery;


import java.util.List;
import java.util.Optional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
public interface DictValueRepository {

    DictValue save(DictValue dictValue);

    Optional<DictValue> findById(Long id);

    Page<DictValue> page(DictValueQuery query);

    void deleteById(Long id);

    void deleteByDictKeyId(Long dictKeyId);

    List<DictValue> findByDictKeyId(Long dictKeyId);

    List<DictValue> findAll();

    List<DictValue> findAll(DictValueQuery query);
}

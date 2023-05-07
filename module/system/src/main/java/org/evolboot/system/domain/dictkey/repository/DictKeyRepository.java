package org.evolboot.system.domain.dictkey.repository;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.DictKeyQuery;


import java.util.List;
import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
public interface DictKeyRepository {

    DictKey save(DictKey dictKey);

    Optional<DictKey> findById(Long id);
    Optional<DictKey> findByKey(String key);

    Page<DictKey> page(DictKeyQuery query);

    void deleteById(Long id);

    List<DictKey> findAll();

    List<DictKey> findAll(DictKeyQuery query);
}

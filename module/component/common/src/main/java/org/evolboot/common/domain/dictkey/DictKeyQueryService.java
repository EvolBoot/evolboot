package org.evolboot.common.domain.dictkey;

import org.evolboot.core.data.Page;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.service.DictKeyQuery;

import java.util.List;
import java.util.Optional;

/**
 * 查询服务 字典key
 *
 * @author evol
 * @date 2023-06-14 20:09:09
 */
public interface DictKeyQueryService {

    DictKey findById(Long id);

    List<DictKey> findAll();

    List<DictKey> findAll(DictKeyQuery query);

    Page<DictKey> page(DictKeyQuery query);

    DictKey findByKey(String key);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<DictKey> findOne(DictKeyQuery query);


}

package org.evolboot.system.domain.dictkey;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.service.DictKeyCreateFactory;
import org.evolboot.system.domain.dictkey.service.DictKeyQuery;
import org.evolboot.system.domain.dictkey.service.DictKeyUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
public interface DictKeyAppService {

    DictKey findById(Long id);

    DictKey findByKey(String key);

    DictKey create(DictKeyCreateFactory.Request request);

    void update(Long id, DictKeyUpdateService.Request request);

    void delete(Long id);

    List<DictKey> findAll();

    List<DictKey> findAll(DictKeyQuery query);

    Page<DictKey> page(DictKeyQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<DictKey> findOne(DictKeyQuery query);

}

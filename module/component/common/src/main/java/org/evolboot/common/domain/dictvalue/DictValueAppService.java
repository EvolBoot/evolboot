package org.evolboot.common.domain.dictvalue;

import org.evolboot.core.data.Page;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.evolboot.common.domain.dictvalue.service.DictValueCreateFactory;
import org.evolboot.common.domain.dictvalue.dto.DictValueQueryRequest;
import org.evolboot.common.domain.dictvalue.service.DictValueUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
public interface DictValueAppService {

    DictValue findById(Long id);

    DictValue create(DictValueCreateFactory.Request request);

    void update(DictValueUpdateService.Request request);

    void delete(Long id);

    void deleteByDictKeyId(Long dictKeyId);

    List<DictValue> findByDictKey(String dictKey);

    List<DictValue> findAll();

    List<DictValue> findAll(DictValueQueryRequest query);

    Page<DictValue> page(DictValueQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<DictValue> findOne(DictValueQueryRequest query);


}

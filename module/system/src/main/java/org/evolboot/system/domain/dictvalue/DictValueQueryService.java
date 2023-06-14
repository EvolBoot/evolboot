package org.evolboot.system.domain.dictvalue;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.dictvalue.entity.DictValue;
import org.evolboot.system.domain.dictvalue.service.DictValueQuery;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 字典Value
 *
 * @author evol
 * @date 2023-06-14 20:09:31
 */
public interface DictValueQueryService {

    DictValue findById(Long id);

    List<DictValue> findAll();

    List<DictValue> findAll(DictValueQuery query);

    Page<DictValue> page(DictValueQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<DictValue> findOne(DictValueQuery query);


}

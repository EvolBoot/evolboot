package org.evolboot.common.domain.dictvalue;

import org.evolboot.core.data.Page;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.evolboot.common.domain.dictvalue.dto.DictValueQueryRequest;

import java.util.List;
import java.util.Optional;

/**
 * 查询服务 字典Value
 *
 * @author evol
 * @date 2023-06-14 20:09:31
 */
public interface DictValueQueryService {

    DictValue findById(Long id);

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

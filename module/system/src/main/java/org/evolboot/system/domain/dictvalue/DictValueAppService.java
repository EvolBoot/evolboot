package org.evolboot.system.domain.dictvalue;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.dictvalue.service.DictValueCreateFactory;
import org.evolboot.system.domain.dictvalue.service.DictValueUpdateService;

import java.util.List;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
public interface DictValueAppService {

    DictValue findById(Long id);

    DictValue create(DictValueCreateFactory.Request request);

    void update(Long id, DictValueUpdateService.Request request);

    void delete(Long id);

    void deleteByDictKeyId(Long dictKeyId);

    List<DictValue> findByDictKey(String dictKey);

    List<DictValue> findAll();

    List<DictValue> findAll(DictValueQuery query);

    Page<DictValue> page(DictValueQuery query);


}

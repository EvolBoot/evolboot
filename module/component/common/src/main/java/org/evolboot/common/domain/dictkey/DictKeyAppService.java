package org.evolboot.common.domain.dictkey;

import org.evolboot.core.data.Page;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.service.DictKeyCreateFactory;
import org.evolboot.common.domain.dictkey.dto.DictKeyQueryRequest;
import org.evolboot.common.domain.dictkey.service.DictKeyUpdateService;

import java.util.Collection;
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



    DictKey create(DictKeyCreateFactory.Request request);

    void update(DictKeyUpdateService.Request request);

    void delete(Long id);

    void delete(Collection<Long> ids);

    List<DictKey> findAll();

    List<DictKey> findAll(DictKeyQueryRequest query);

    Page<DictKey> page(DictKeyQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<DictKey> findOne(DictKeyQueryRequest query);

}

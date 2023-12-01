package org.evolboot.common.domain.dictkey;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.common.CommonI18nMessage;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.common.domain.dictkey.dto.DictKeyQueryRequest;
import org.evolboot.common.domain.dictkey.service.DictKeySupportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 查询服务 字典key
 *
 * @author evol
 * @date 2023-06-14 20:09:09
 */
@Slf4j
@Service
public class DictKeyQueryServiceImpl  implements DictKeyQueryService {

    private final DictKeyRepository repository;
    private final DictKeySupportService supportService;

    protected DictKeyQueryServiceImpl(DictKeyRepository repository, DictKeySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public DictKey findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<DictKey> findAll() {
        return repository.findAll();
    }


    @Override
    public List<DictKey> findAll(DictKeyQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<DictKey> page(DictKeyQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public DictKey findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(CommonI18nMessage.DictKey.NOT_FOUND)));
    }


    @Override
    public Optional<DictKey> findOne(DictKeyQueryRequest query) {
        return repository.findOne(query);
    }
}

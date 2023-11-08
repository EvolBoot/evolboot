package org.evolboot.system.domain.dictkey;

import org.evolboot.core.data.Page;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.system.domain.dictkey.service.DictKeySupportService;

import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.service.DictKeyQuery;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

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
    public List<DictKey> findAll(DictKeyQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<DictKey> page(DictKeyQuery query) {
        return repository.page(query);
    }

    @Override
    public DictKey findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(SystemI18nMessage.DictKey.NOT_FOUND)));
    }


    @Override
    public Optional<DictKey> findOne(DictKeyQuery query) {
        return repository.findOne(query);
    }
}

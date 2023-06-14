package org.evolboot.system.domain.dictkey;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemAccessAuthorities;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.system.domain.dictkey.service.DictKeyCreateFactory;
import org.evolboot.system.domain.dictkey.service.DictKeySupportService;
import org.evolboot.system.domain.dictkey.service.DictKeyUpdateService;

import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.service.DictKeyQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class DictKeyQueryServiceImpl extends DictKeySupportService implements DictKeyQueryService {

    protected DictKeyQueryServiceImpl(DictKeyRepository repository) {
        super(repository);
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
    public Optional<DictKey> findOne(DictKeyQuery query) {
        return repository.findOne(query);
    }
}

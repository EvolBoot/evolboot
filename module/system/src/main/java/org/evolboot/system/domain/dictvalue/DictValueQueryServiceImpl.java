package org.evolboot.system.domain.dictvalue;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.Sort;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemAccessAuthorities;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.dictvalue.repository.DictValueRepository;
import org.evolboot.system.domain.dictvalue.service.DictValueCreateFactory;
import org.evolboot.system.domain.dictvalue.service.DictValueSupportService;
import org.evolboot.system.domain.dictvalue.service.DictValueUpdateService;

import org.evolboot.system.domain.dictvalue.entity.DictValue;
import org.evolboot.system.domain.dictvalue.service.DictValueQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 字典Value
 *
 * @author evol
 * @date 2023-06-14 20:09:31
 */
@Slf4j
@Service
public class DictValueQueryServiceImpl  implements DictValueQueryService {

    private final DictValueRepository repository;
    private final DictValueSupportService supportService;

    protected DictValueQueryServiceImpl(DictValueRepository repository, DictValueSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public DictValue findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    public List<DictValue> findAll() {
        return repository.findAll();
    }


    @Override
    public List<DictValue> findAll(DictValueQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<DictValue> page(DictValueQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<DictValue> findOne(DictValueQuery query) {
        return repository.findOne(query);
    }
}

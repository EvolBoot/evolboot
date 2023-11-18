package org.evolboot.common.domain.dictvalue;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.common.domain.dictkey.DictKeyQueryService;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.evolboot.common.domain.dictvalue.repository.DictValueRepository;
import org.evolboot.common.domain.dictvalue.service.DictValueCreateFactory;
import org.evolboot.common.domain.dictvalue.service.DictValueQuery;
import org.evolboot.common.domain.dictvalue.service.DictValueSupportService;
import org.evolboot.common.domain.dictvalue.service.DictValueUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Slf4j
@Service
public class DictValueAppServiceImpl  implements DictValueAppService {


    private final DictValueCreateFactory factory;
    private final DictValueUpdateService updateService;

    private final DictValueRepository repository;

    private final DictValueSupportService supportService;

    private final DictKeyQueryService dictKeyQueryService;

    protected DictValueAppServiceImpl(DictValueRepository repository, DictValueCreateFactory factory, DictValueUpdateService updateService, DictValueSupportService supportService, DictKeyQueryService dictKeyQueryService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
        this.dictKeyQueryService = dictKeyQueryService;
    }

    @Override
    public DictValue findById(Long id) {
        return supportService.findById(id);
    }
    @Override
    @Transactional
    public DictValue create(DictValueCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(DictValueUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByDictKeyId(Long dictKeyId) {
        repository.deleteByDictKeyId(dictKeyId);
    }

    public List<DictValue> findByDictKey(String dictKey) {
        return repository.findByDictKeyId(dictKeyQueryService.findByKey(dictKey).id());
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

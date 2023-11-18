package org.evolboot.common.domain.dictkey;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.shared.event.dict.DictKeyDeleteEvent;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.common.domain.dictkey.service.DictKeyCreateFactory;
import org.evolboot.common.domain.dictkey.service.DictKeyQuery;
import org.evolboot.common.domain.dictkey.service.DictKeySupportService;
import org.evolboot.common.domain.dictkey.service.DictKeyUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@Service
public class DictKeyAppServiceImpl  implements DictKeyAppService {


    private final DictKeyCreateFactory factory;
    private final DictKeyUpdateService updateService;

    private final DictKeyRepository repository;

    private final DictKeySupportService supportService;

    private final EventPublisher eventPublisher;

    protected DictKeyAppServiceImpl(DictKeyRepository repository, DictKeyCreateFactory factory, DictKeyUpdateService updateService, DictKeySupportService supportService, EventPublisher eventPublisher) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public DictKey findById(Long id) {
        return supportService.findById(id);
    }



    @Override
    @Transactional
    public DictKey create(DictKeyCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(DictKeyUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
        // 删除下级
        eventPublisher.publishEvent(new DictKeyDeleteEvent(id));
    }

    @Override
    public void delete(Collection<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
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

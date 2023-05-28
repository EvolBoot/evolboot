package org.evolboot.system.domain.dictkey;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.shared.event.dict.DictKeyDeleteEvent;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.system.domain.dictkey.service.DictKeyCreateFactory;
import org.evolboot.system.domain.dictkey.service.DictKeyQuery;
import org.evolboot.system.domain.dictkey.service.DictKeySupportService;
import org.evolboot.system.domain.dictkey.service.DictKeyUpdateService;
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
public class DictKeyAppServiceImpl extends DictKeySupportService implements DictKeyAppService {


    private final DictKeyCreateFactory factory;
    private final DictKeyUpdateService updateService;

    private final EventPublisher eventPublisher;

    protected DictKeyAppServiceImpl(DictKeyRepository repository, DictKeyCreateFactory factory, DictKeyUpdateService updateService, EventPublisher eventPublisher) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public DictKey findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(SystemI18nMessage.DictKey.NOT_FOUND)));
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

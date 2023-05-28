package org.evolboot.system.domain.qa;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.qa.entity.Qa;
import org.evolboot.system.domain.qa.repository.QaRepository;
import org.evolboot.system.domain.qa.service.QaCreateFactory;
import org.evolboot.system.domain.qa.service.QaQuery;
import org.evolboot.system.domain.qa.service.QaSupportService;
import org.evolboot.system.domain.qa.service.QaUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * QA
 *
 * @author evol
 */
@Slf4j
@Service
public class QaAppServiceImpl extends QaSupportService implements QaAppService {


    private final QaCreateFactory factory;
    private final QaUpdateService updateService;

    protected QaAppServiceImpl(QaRepository repository, QaCreateFactory factory, QaUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Qa create(QaCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(QaUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Qa> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Qa> findAll(QaQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Qa> page(QaQuery query) {
        return repository.page(query);
    }


}

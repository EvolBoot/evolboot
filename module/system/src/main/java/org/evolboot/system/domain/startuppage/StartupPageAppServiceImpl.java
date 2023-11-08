package org.evolboot.system.domain.startuppage;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.startuppage.entity.StartupPage;
import org.evolboot.system.domain.startuppage.repository.StartupPageRepository;
import org.evolboot.system.domain.startuppage.service.StartupPageCreateFactory;
import org.evolboot.system.domain.startuppage.service.StartupPageQuery;
import org.evolboot.system.domain.startuppage.service.StartupPageSupportService;
import org.evolboot.system.domain.startuppage.service.StartupPageUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 启动页
 *
 * @author evol
 */
@Slf4j
@Service
public class StartupPageAppServiceImpl  implements StartupPageAppService {


    private final StartupPageCreateFactory factory;
    private final StartupPageUpdateService updateService;

    private final StartupPageRepository repository;

    private final StartupPageSupportService supportService;

    protected StartupPageAppServiceImpl(StartupPageRepository repository, StartupPageCreateFactory factory, StartupPageUpdateService updateService, StartupPageSupportService supportService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public StartupPage findById(Long id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public StartupPage create(StartupPageCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(StartupPageUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<StartupPage> findAll() {
        return repository.findAll();
    }


    @Override
    public List<StartupPage> findAll(StartupPageQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<StartupPage> page(StartupPageQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<StartupPage> findOne(StartupPageQuery query) {
        return repository.findOne(query);
    }

}

package org.evolboot.content.domain.startuppage;

import org.evolboot.content.domain.startuppage.repository.StartupPageRepository;
import org.evolboot.core.data.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 启动页
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultStartupPageAppService extends StartupPageSupportService implements StartupPageAppService {


    private final StartupPageCreateFactory factory;
    private final StartupPageUpdateService updateService;

    protected DefaultStartupPageAppService(StartupPageRepository repository, StartupPageCreateFactory factory, StartupPageUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public StartupPage create(StartupPageCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, StartupPageUpdateService.Request request) {
        updateService.execute(id, request);
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

}

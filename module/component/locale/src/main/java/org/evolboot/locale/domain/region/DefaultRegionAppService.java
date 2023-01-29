package org.evolboot.locale.domain.region;

import org.evolboot.core.data.Page;
import org.evolboot.locale.domain.region.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author evol
 */
@Service
public class DefaultRegionAppService extends RegionSupportService implements RegionAppService {


    private final RegionCreateFactory factory;
    private final RegionUpdateService updateService;

    protected DefaultRegionAppService(RegionRepository repository, RegionCreateFactory factory, RegionUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Long create(RegionCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, RegionUpdateService.Request request) {
        updateService.execute(id, request);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<Region> list() {
        return repository.findAll();
    }

    @Override
    public List<Region> findAllByEnableIsTrue() {
        return repository.findAllByEnableIsTrue();
    }

    @Override
    public Page<Region> page(RegionQuery query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public void enable(Long id) {
        Region region = findById(id);
        region.enable();
        repository.save(region);
    }

    @Override
    @Transactional
    public void disable(Long id) {
        Region region = findById(id);
        region.disable();
        repository.save(region);
    }

}

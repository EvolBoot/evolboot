package org.evolboot.content.domain.banner;

import org.evolboot.content.domain.banner.repository.BannerRepository;
import org.evolboot.core.data.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * banner
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class DefaultBannerAppService extends BannerSupportService implements BannerAppService {


    private final BannerCreateFactory factory;
    private final BannerUpdateService updateService;

    protected DefaultBannerAppService(BannerRepository repository, BannerCreateFactory factory, BannerUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Banner create(BannerCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, BannerUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Banner> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Banner> findAll(BannerQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Banner> page(BannerQuery query) {
        return repository.page(query);
    }

}

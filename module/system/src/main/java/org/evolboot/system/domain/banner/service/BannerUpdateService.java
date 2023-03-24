package org.evolboot.system.domain.banner.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.BannerRequestBase;
import org.evolboot.system.domain.banner.repository.BannerRepository;
import org.springframework.stereotype.Service;

/**
 * banner
 *
 * @author evol
 * 
 */
@Slf4j
@Service
public class BannerUpdateService extends BannerSupportService {
    protected BannerUpdateService(BannerRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Banner banner = findById(id);
        banner.setSort(request.getSort());
        banner.setShow(request.getShow());
        banner.setLocales(request.getLocales());
        repository.save(banner);
    }

    public static class Request extends BannerRequestBase {
    }

}

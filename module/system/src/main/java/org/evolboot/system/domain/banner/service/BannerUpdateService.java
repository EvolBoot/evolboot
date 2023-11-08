package org.evolboot.system.domain.banner.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.banner.entity.Banner;
import org.evolboot.system.domain.banner.repository.BannerRepository;
import org.springframework.stereotype.Service;

/**
 * banner
 *
 * @author evol
 */
@Slf4j
@Service
public class BannerUpdateService {

    private final BannerRepository repository;
    private final BannerSupportService supportService;

    protected BannerUpdateService(BannerRepository repository, BannerSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Banner banner = supportService.findById(request.getId());
        banner.setSort(request.getSort());
        banner.setShow(request.getShow());
        banner.setLocales(request.getLocales());
        repository.save(banner);
    }

    @Getter
    @Setter
    public static class Request extends BannerRequestBase {
        private Long id;
    }

}

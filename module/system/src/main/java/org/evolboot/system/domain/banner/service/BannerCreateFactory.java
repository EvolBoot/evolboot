package org.evolboot.system.domain.banner.service;

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
public class BannerCreateFactory {

    private final BannerRepository repository;
    private final BannerSupportService supportService;

    protected BannerCreateFactory(BannerRepository repository, BannerSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public Banner execute(Request request) {
        Banner banner = new Banner(
                request.getSort(),
                request.getShow(),
                request.getLocales()
        );
        repository.save(banner);
        return banner;
    }

    public static class Request extends BannerRequestBase {

    }

}

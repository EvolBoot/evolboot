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
public class BannerCreateFactory extends BannerSupportService {
    protected BannerCreateFactory(BannerRepository repository) {
        super(repository);
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

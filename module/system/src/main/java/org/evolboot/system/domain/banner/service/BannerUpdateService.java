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
public class BannerUpdateService extends BannerSupportService {
    protected BannerUpdateService(BannerRepository repository) {
        super(repository);
    }

    public void execute( Request request) {
        Banner banner = findById(request.getId());
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

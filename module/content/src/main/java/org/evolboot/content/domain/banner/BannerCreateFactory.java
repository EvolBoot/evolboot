package org.evolboot.content.domain.banner;

import org.evolboot.content.domain.banner.repository.BannerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * banner
 *
 * @author evol
 * 
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

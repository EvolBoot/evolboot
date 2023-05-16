package org.evolboot.system.domain.banner;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.banner.repository.BannerRepository;
import org.evolboot.system.domain.banner.service.BannerSupportService;
import org.springframework.stereotype.Service;

/**
 * banner
 *
 * @author evol
 */
@Service
@Slf4j
public class BannerListener extends BannerSupportService {

    protected BannerListener(BannerRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

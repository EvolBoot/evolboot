package org.evolboot.system.domain.banner.listener;

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
public class BannerListener {

    private final BannerRepository repository;

    private final BannerSupportService supportService;

    protected BannerListener(BannerRepository repository, BannerSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}

package org.evolboot.system.domain.banner.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.system.SystemI18nMessage;
import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.repository.BannerRepository;

/**
 * banner
 *
 * @author evol
 */
@Slf4j
public abstract class BannerSupportService {

    protected final BannerRepository repository;

    protected BannerSupportService(BannerRepository repository) {
        this.repository = repository;
    }

    public Banner findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(SystemI18nMessage.Banner.notFound()));
    }

}

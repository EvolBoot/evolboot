package org.evolboot.content.domain.banner.service;

import org.evolboot.content.ContentI18nMessage;
import org.evolboot.content.domain.banner.Banner;
import org.evolboot.content.domain.banner.repository.BannerRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * banner
 *
 * @author evol
 * 
 */
@Slf4j
public abstract class BannerSupportService {

    protected final BannerRepository repository;

    protected BannerSupportService(BannerRepository repository) {
        this.repository = repository;
    }

    public Banner findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(ContentI18nMessage.Banner.notFound()));
    }

}

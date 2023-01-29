package org.evolboot.locale.domain.region;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.locale.LocaleI18nMessage;
import org.evolboot.locale.domain.region.repository.RegionRepository;

/**
 * @author evol
 */
public abstract class RegionSupportService {

    final RegionRepository repository;

    protected RegionSupportService(RegionRepository repository) {
        this.repository = repository;
    }

    public Region findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(LocaleI18nMessage.Region.notFound()));
    }

}

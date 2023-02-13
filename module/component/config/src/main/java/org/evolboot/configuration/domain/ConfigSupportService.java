package org.evolboot.configuration.domain;

import org.evolboot.configuration.ConfigI18nMessage;
import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.evolboot.core.exception.DomainNotFoundException;

/**
 * @author evol
 */
public abstract class ConfigSupportService {

    protected final ConfigurationRepository repository;

    protected ConfigSupportService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public Config findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(ConfigI18nMessage.notFound()));
    }
}

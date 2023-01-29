package org.evolboot.configuration.domain;

import org.evolboot.configuration.ConfigurationI18nMessage;
import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.evolboot.core.exception.DomainNotFoundException;

/**
 * @author evol
 */
public abstract class ConfigurationSupportService {

    protected final ConfigurationRepository repository;

    protected ConfigurationSupportService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public org.evolboot.configuration.domain.Configuration findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(ConfigurationI18nMessage.notFound()));
    }
}

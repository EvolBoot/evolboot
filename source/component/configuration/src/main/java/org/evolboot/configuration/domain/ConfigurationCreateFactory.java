package org.evolboot.configuration.domain;

import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class ConfigurationCreateFactory extends ConfigurationSupportService {
    protected ConfigurationCreateFactory(ConfigurationRepository repository) {
        super(repository);
    }

    public org.evolboot.configuration.domain.PropertyValue execute(String key, org.evolboot.configuration.domain.PropertyValue value, Scope scope) {
        value.check();
        org.evolboot.configuration.domain.Configuration configuration = new org.evolboot.configuration.domain.Configuration(
                key, value, scope
        );
        repository.save(configuration);
        return value;
    }
}

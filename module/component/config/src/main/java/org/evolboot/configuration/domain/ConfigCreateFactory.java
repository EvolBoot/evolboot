package org.evolboot.configuration.domain;

import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class ConfigCreateFactory extends ConfigSupportService {
    protected ConfigCreateFactory(ConfigurationRepository repository) {
        super(repository);
    }

    public org.evolboot.configuration.domain.PropertyValue execute(String key, org.evolboot.configuration.domain.PropertyValue value, Scope scope) {
        value.check();
        Config config = new Config(
                key, value, scope
        );
        repository.save(config);
        return value;
    }
}

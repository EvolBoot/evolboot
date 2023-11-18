package org.evolboot.common.domain.config.serivce;

import org.evolboot.common.domain.config.entity.Config;
import org.evolboot.common.domain.config.entity.Scope;
import org.evolboot.common.domain.config.repository.ConfigRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class ConfigCreateFactory  {

    private final ConfigRepository repository;
    
    protected ConfigCreateFactory(ConfigRepository repository) {
        this.repository = repository;
    }

    public PropertyValue execute(String key, PropertyValue value, Scope scope) {
        value.check();
        Config config = new Config(
                key, value, scope
        );
        repository.save(config);
        return value;
    }
}

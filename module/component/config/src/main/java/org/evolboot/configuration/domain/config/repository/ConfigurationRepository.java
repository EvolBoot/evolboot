package org.evolboot.configuration.domain.config.repository;

import org.evolboot.configuration.domain.config.Config;

import java.util.Optional;

/**
 * @author evol
 */
public interface ConfigurationRepository {

    Optional<Config> findByKey(String key);

    Config save(Config config);

}

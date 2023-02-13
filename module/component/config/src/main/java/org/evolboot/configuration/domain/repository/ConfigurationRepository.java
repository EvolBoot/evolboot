package org.evolboot.configuration.domain.repository;

import org.evolboot.configuration.domain.Config;

import java.util.Optional;

/**
 * @author evol
 */
public interface ConfigurationRepository {

    Optional<Config> findByKey(String key);

    Config save(Config config);

}

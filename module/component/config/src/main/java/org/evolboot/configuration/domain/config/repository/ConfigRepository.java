package org.evolboot.configuration.domain.config.repository;

import org.evolboot.configuration.domain.config.entity.Config;

import java.util.Optional;

/**
 * @author evol
 */
public interface ConfigRepository {

    Optional<Config> findByKey(String key);

    Config save(Config config);

}

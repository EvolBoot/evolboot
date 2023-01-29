package org.evolboot.configuration.domain.repository;

import org.evolboot.configuration.domain.Configuration;

import java.util.Optional;

/**
 * @author evol
 */
public interface ConfigurationRepository {

    Optional<Configuration> findByKey(String key);

    Configuration save(Configuration config);

}

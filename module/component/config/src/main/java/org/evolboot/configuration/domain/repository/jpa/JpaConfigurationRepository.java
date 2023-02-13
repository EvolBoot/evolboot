package org.evolboot.configuration.domain.repository.jpa;

import org.evolboot.configuration.domain.Config;
import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.evolboot.shared.cache.RedisCacheName;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author evol
 */
@Repository
public interface JpaConfigurationRepository extends ConfigurationRepository, QuerydslPredicateExecutor<Config>, JpaRepository<Config, String> {

    String CACHE_NAME = RedisCacheName.CONFIGURATION_CACHE_NAME;

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#p0.key")
    Config save(Config config);

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#p0")
    Optional<Config> findByKey(String key);
}

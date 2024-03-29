package org.evolboot.common.domain.config.serivce;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.common.CommonI18nMessage;
import org.evolboot.common.domain.config.entity.Config;
import org.evolboot.common.domain.config.repository.ConfigRepository;
import org.evolboot.core.exception.DomainNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Slf4j
@Service
public class ConfigSupportService {

    protected final ConfigRepository repository;

    protected ConfigSupportService(ConfigRepository repository) {
        this.repository = repository;
    }

    public Config findByKey(String key) {
        return repository.findByKey(key).orElseThrow(() -> new DomainNotFoundException(CommonI18nMessage.Config.notFound()));
    }
}

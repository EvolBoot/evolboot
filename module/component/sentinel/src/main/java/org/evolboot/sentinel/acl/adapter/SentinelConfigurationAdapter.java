package org.evolboot.sentinel.acl.adapter;

import org.evolboot.configuration.domain.config.ConfigAppService;
import org.evolboot.sentinel.SentinelRole;
import org.evolboot.sentinel.acl.port.SentinelConfigurationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class SentinelConfigurationAdapter implements SentinelConfigurationPort {

    private final ConfigAppService configAppService;

    public SentinelConfigurationAdapter(ConfigAppService configAppService) {
        this.configAppService = configAppService;
    }

    @Override
    public SentinelRole getSentinelRole() {
        return configAppService.getByKey(SentinelRole.KEY, SentinelRole.class);
    }

    @Override
    public void save(SentinelRole sentinelRole) {
        configAppService.setPropertyValue(sentinelRole);
    }
}

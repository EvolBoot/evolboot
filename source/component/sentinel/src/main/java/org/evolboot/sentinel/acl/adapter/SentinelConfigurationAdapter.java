package org.evolboot.sentinel.acl.adapter;

import org.evolboot.configuration.domain.ConfigurationAppService;
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

    private final ConfigurationAppService configurationAppService;

    public SentinelConfigurationAdapter(ConfigurationAppService configurationAppService) {
        this.configurationAppService = configurationAppService;
    }

    @Override
    public SentinelRole getSentinelRole() {
        return configurationAppService.getByKey(SentinelRole.KEY, SentinelRole.class);
    }

    @Override
    public void save(SentinelRole sentinelRole) {
        configurationAppService.setPropertyValue(sentinelRole);
    }
}

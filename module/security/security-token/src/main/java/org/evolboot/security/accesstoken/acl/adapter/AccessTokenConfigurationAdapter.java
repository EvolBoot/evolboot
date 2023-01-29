package org.evolboot.security.accesstoken.acl.adapter;

import org.evolboot.configuration.domain.ConfigurationAppService;
import org.evolboot.configuration.domain.system.SystemConfig;
import org.evolboot.security.accesstoken.acl.port.AccessTokenConfigurationPort;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class AccessTokenConfigurationAdapter implements AccessTokenConfigurationPort {

    private final ConfigurationAppService configurationAppService;

    public AccessTokenConfigurationAdapter(ConfigurationAppService configurationAppService) {
        this.configurationAppService = configurationAppService;
    }

    @Override
    public Boolean enableSingleLogin() {
        return configurationAppService.getByKey(SystemConfig.KEY, SystemConfig.class).getEnableSingleLogin();
    }
}

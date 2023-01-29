package org.evolboot.identity.acl.adapter;

import org.evolboot.configuration.domain.ConfigurationAppService;
import org.evolboot.configuration.domain.system.SystemConfig;
import org.evolboot.identity.acl.port.IdentityConfigurationPort;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class IdentityConfigurationAdapter implements IdentityConfigurationPort {

    private final ConfigurationAppService configurationAppService;

    public IdentityConfigurationAdapter(ConfigurationAppService configurationAppService) {
        this.configurationAppService = configurationAppService;
    }

    @Override
    public SystemConfig getSystemConfig() {
        return configurationAppService.getByKey(SystemConfig.KEY, SystemConfig.class);
    }

    @Override
    public Boolean enableRegisterSms() {
        return getSystemConfig().getEnableRegisterSms();
    }

    @Override
    public Boolean enableRegisterEmailValidation() {
        return getSystemConfig().getEnableRegisterEmailValidation();
    }
}

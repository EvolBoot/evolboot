package org.evolboot.identity.acl.adapter;

import org.evolboot.configuration.domain.ConfigAppService;
import org.evolboot.configuration.domain.system.SystemConfig;
import org.evolboot.identity.acl.client.IdentityConfigClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class IdentityConfigAdapter implements IdentityConfigClient {

    private final ConfigAppService configAppService;

    public IdentityConfigAdapter(ConfigAppService configAppService) {
        this.configAppService = configAppService;
    }

    @Override
    public SystemConfig getSystemConfig() {
        return configAppService.getByKey(SystemConfig.KEY, SystemConfig.class);
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

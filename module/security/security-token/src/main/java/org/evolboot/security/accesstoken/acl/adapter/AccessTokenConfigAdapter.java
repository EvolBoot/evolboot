package org.evolboot.security.accesstoken.acl.adapter;

import org.evolboot.common.domain.config.ConfigAppService;
import org.evolboot.common.domain.config.model.system.SystemConfig;
import org.evolboot.security.accesstoken.acl.client.AccessTokenConfigClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class AccessTokenConfigAdapter implements AccessTokenConfigClient {

    private final ConfigAppService configAppService;

    public AccessTokenConfigAdapter(ConfigAppService configAppService) {
        this.configAppService = configAppService;
    }

    @Override
    public Boolean enableSingleLogin() {
        return configAppService.getByKey(SystemConfig.KEY, SystemConfig.class).getEnableSingleLogin();
    }
}

package org.evolboot.sms.acl.adapter;

import org.evolboot.common.domain.config.ConfigAppService;
import org.evolboot.common.domain.config.model.sms.SmsConfig;
import org.evolboot.sms.acl.client.SmsConfigClient;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class SmsConfigAdapter implements SmsConfigClient {

    private final ConfigAppService configAppService;

    public SmsConfigAdapter(ConfigAppService configAppService) {
        this.configAppService = configAppService;
    }

    @Override
    public SmsConfig getSmsConfig() {
        return configAppService.getByKey(SmsConfig.KEY, SmsConfig.class);
    }
}

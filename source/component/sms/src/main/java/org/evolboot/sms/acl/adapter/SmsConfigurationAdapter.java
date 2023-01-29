package org.evolboot.sms.acl.adapter;

import org.evolboot.configuration.domain.ConfigurationAppService;
import org.evolboot.configuration.domain.sms.SmsConfig;
import org.evolboot.sms.acl.port.SmsConfigurationPort;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class SmsConfigurationAdapter implements SmsConfigurationPort {

    private final ConfigurationAppService configurationAppService;

    public SmsConfigurationAdapter(ConfigurationAppService configurationAppService) {
        this.configurationAppService = configurationAppService;
    }

    @Override
    public SmsConfig getSmsConfig() {
        return configurationAppService.getByKey(SmsConfig.KEY, SmsConfig.class);
    }
}

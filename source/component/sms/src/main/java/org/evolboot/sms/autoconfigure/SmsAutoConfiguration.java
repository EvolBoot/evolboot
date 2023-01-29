package org.evolboot.sms.autoconfigure;

import org.evolboot.shared.sms.SmsSendChannel;
import org.evolboot.sms.domain.SmsSender;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author evol
 */
@Slf4j
@Configuration
public class SmsAutoConfiguration {

    @Bean
    public Map<SmsSendChannel, SmsSender> getSmsSender(List<SmsSender> list) {
        Map<SmsSendChannel, SmsSender> map = Maps.newHashMap();
        list.forEach(
                smsSender -> {
                    map.put(smsSender.getSmsSendChannel(), smsSender);
                }
        );
        return map;
    }

}

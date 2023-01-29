package org.evolboot.core.autoconfigure;

import org.evolboot.core.DefaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * @author evol
 * 
 */
@Configuration
@Slf4j
public class DefaultAutoConfiguration {


    public DefaultAutoConfiguration(DefaultConfigProperties properties) {

        log.info("默认配置:{}", properties);
        Locale locale = Locale.forLanguageTag(properties.getLanguage());
        if ("".equals(locale.toString())) {
            log.info("警告：配置的语言:{} 不存在 ，切换为英文", properties.getLanguage());
            locale = Locale.US;
        }
        log.info("默认语言:{}", locale.toLanguageTag());

        DefaultConfig.setDefaultConfig(DefaultConfig
                .builder()
                .timeZone(properties.getTimeZone())
                .defaultLanguage(locale)
                .testKey(properties.getTestKey())
                .testModel(properties.getTestMode())
                .build());
    }

}

package org.evolboot.identity.autoconfigure;

import org.evolboot.identity.domain.user.UserConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 * 
 */
@EnableConfigurationProperties(UserProperties.class)
@Configuration
@Slf4j
public class UserAutoConfiguration {

    public UserAutoConfiguration(UserProperties properties) {
        log.info("初始化用户配置: {}", properties.toString());
        UserConfiguration.setValue(UserConfiguration.Value.builder()
                .defaultAvatar(properties.getAvatar())
                .build());
    }

}

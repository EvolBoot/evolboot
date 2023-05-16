package org.evolboot.security.accesstoken.autoconfigure;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationProvider;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author evol
 */
@EnableConfigurationProperties(AccessTokenProperties.class)
@Configuration
@Slf4j
public class AccessTokenAutoConfiguration {

    @Bean
    public Map<AuthenticationTokenType, AuthenticationProvider> providerMap(List<AuthenticationProvider> providers) {
        Map<AuthenticationTokenType, AuthenticationProvider> providerMap = Maps.newHashMap();
        providers.forEach(provider -> {
            providerMap.put(provider.getAuthenticationTokenType(), provider);
        });
        return providerMap;
    }


}

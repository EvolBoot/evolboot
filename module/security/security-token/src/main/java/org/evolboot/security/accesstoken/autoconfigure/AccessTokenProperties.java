package org.evolboot.security.accesstoken.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author evol
 */
@Getter
@Setter
@ConfigurationProperties(AccessTokenProperties.CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class AccessTokenProperties {

    public final static String CONFIGURATION_PREFIX = "security.access-token";

    private Long timeout;

}

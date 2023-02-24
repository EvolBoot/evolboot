package org.evolboot.security.api.autoconfigure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 */
@Data
@Getter
@Setter
@Configuration
@ConfigurationProperties(SecurityDefaultConfigProperties.CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class SecurityDefaultConfigProperties {

    public final static String CONFIGURATION_PREFIX = "evolpn.default";

    private Boolean testMode = false;

    private String testKey = "";

}

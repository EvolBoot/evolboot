package org.evolboot.core.autoconfigure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.evolboot.core.autoconfigure.DefaultConfigProperties.CONFIGURATION_PREFIX;

/**
 * @author evol
 */
@Data
@Getter
@Setter
@Configuration
@ConfigurationProperties(CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class DefaultConfigProperties {

    public final static String CONFIGURATION_PREFIX = "evolpn.default";

    private String language = "en-US";

    private String timeZone = "GMT+8";


    private Boolean testMode = false;

    private String testKey = "";
}

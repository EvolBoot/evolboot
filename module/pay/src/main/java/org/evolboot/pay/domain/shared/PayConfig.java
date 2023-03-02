package org.evolboot.pay.domain.shared;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.evolboot.pay.domain.shared.PayConfig.CONFIGURATION_PREFIX;

/**
 * @author evol
 */
@Getter
@ToString
@Setter
@ConfigurationProperties(prefix = CONFIGURATION_PREFIX)
@Configuration
public class PayConfig {

    public final static String CONFIGURATION_PREFIX = "evolpn.pay";

    private String domain;

}

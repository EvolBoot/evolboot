package org.evolboot.apidoc.autoconfigure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author evol
 */
@Data
@Getter
@Setter
@ConfigurationProperties(DefaultOpenApiProperties.CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class DefaultOpenApiProperties {

    public final static String CONFIGURATION_PREFIX = "springdoc";

    private String title;
    private String version;

}

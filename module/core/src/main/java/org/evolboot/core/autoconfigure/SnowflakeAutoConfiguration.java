package org.evolboot.core.autoconfigure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.lang.Snowflake;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.evolboot.core.autoconfigure.SnowflakeAutoConfiguration.SnowflakeConfigProperties.CONFIGURATION_PREFIX;

/**
 * @author evol
 */
@Configuration
@EnableConfigurationProperties(SnowflakeAutoConfiguration.SnowflakeConfigProperties.class)
@Slf4j
public class SnowflakeAutoConfiguration {

    public SnowflakeAutoConfiguration(SnowflakeConfigProperties snowflakeConfigProperties) {
        log.info("Snowflake Host:{}", snowflakeConfigProperties.getSnowflakeHost());
        IdGenerate.setSnowflake(new Snowflake(snowflakeConfigProperties.getSnowflakeHost()));
    }

    @Data
    @Getter
    @Setter
    @ConfigurationProperties(CONFIGURATION_PREFIX)
    @Slf4j
    @ToString
    public static class SnowflakeConfigProperties {

        public final static String CONFIGURATION_PREFIX = "evol";

        private Long snowflakeHost = 0L;

    }
}

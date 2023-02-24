package org.evolboot.core.autoconfigure;

import cn.hutool.crypto.asymmetric.RSA;
import org.evolboot.core.service.crypto.rsa.DefaultRsaService;
import org.evolboot.core.service.crypto.rsa.RsaConfig;
import org.evolboot.core.service.crypto.rsa.RsaService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author evol
 * 
 */
@Configuration
@EnableConfigurationProperties(RsaAutoConfiguration.RsaConfigProperties.class)
@Slf4j
public class RsaAutoConfiguration {


    @Bean
    public RsaService rsaAppService(RsaConfigProperties properties) {
        String rsaPrivate = properties.getRsaPrivate();
        String rsaPublic = properties.getRsaPublic();
        RSA rsa = new RSA(rsaPrivate, rsaPublic);
        RsaConfig.setRsa(rsa);
        return new DefaultRsaService(rsa);
    }


    @Data
    @Getter
    @Setter
    @ConfigurationProperties(RsaConfigProperties.CONFIGURATION_PREFIX)
    @Slf4j
    @ToString
    public static class RsaConfigProperties {

        public final static String CONFIGURATION_PREFIX = "evolpn.rsa";

        public String rsaPrivate;

        public String rsaPublic;


    }

}

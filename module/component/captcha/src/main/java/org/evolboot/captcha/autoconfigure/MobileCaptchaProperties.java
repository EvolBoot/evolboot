package org.evolboot.captcha.autoconfigure;

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
@ConfigurationProperties(MobileCaptchaProperties.CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class MobileCaptchaProperties {

    public final static String CONFIGURATION_PREFIX = "evolpn.captcha.mobile";

    private Long timeout;

    private Integer limitVerifyCount;

    private Long interval;
}

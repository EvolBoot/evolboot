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
@ConfigurationProperties(ImageCaptchaProperties.CONFIGURATION_PREFIX)
@Slf4j
@ToString
public class ImageCaptchaProperties {

    public final static String CONFIGURATION_PREFIX = "evolpn.captcha.image";

    private Long timeout;
}

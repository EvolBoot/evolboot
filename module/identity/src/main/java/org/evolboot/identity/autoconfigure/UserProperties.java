package org.evolboot.identity.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@ConfigurationProperties("evolpn.identity.user")
@Slf4j
@ToString
public class UserProperties {
    private String avatar;

}

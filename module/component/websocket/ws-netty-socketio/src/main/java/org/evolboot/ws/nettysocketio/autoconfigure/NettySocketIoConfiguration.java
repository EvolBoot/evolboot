package org.evolboot.ws.nettysocketio.autoconfigure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static org.evolboot.ws.nettysocketio.autoconfigure.NettySocketIoConfiguration.CONFIGURATION_PREFIX;


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
public class NettySocketIoConfiguration {

    public final static String CONFIGURATION_PREFIX = "evolpn.socketio";

    private Integer port;

    private String host;


}

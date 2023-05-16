package org.evolboot.ws.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Websocket 服务
 *
 * @author evol
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WsService {


}

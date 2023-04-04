package org.evolboot.ws.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author evol
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WsService {


}

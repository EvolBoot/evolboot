package org.evolboot.ws.core;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.function.Function;

/**
 * @author evol
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WsOnMessage {

    /**
     * action
     *
     * @return
     */
    String value() default "";

    Class<?> convert() default Object.class;
}

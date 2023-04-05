package org.evolboot.ws.core;


import java.lang.annotation.*;

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

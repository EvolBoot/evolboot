package org.evolboot.core.annotation;

import java.lang.annotation.*;

/**
 * @author evol
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    String value() default "";

    boolean excludeUnserializable() default true;
}

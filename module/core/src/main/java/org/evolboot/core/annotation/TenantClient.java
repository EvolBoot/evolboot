package org.evolboot.core.annotation;

import java.lang.annotation.*;

/**
 * @author evol
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantClient {
}

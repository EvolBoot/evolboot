package org.evolboot.core.annotation;

import java.lang.annotation.*;

/**
 * 权限模块注解
 * 标注在 *AccessAuthorities 接口上，用于指定模块信息
 *
 * @author evol
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityModule {

    /**
     * 模块标识，如: identity, system, finance
     */
    String value();

    /**
     * 模块中文名称，如: 身份管理, 系统管理
     */
    String label() default "";
}

package org.evolboot.core.annotation;

import java.lang.annotation.*;

/**
 * 权限资源注解
 * 标注在 *AccessAuthorities 接口的嵌套接口上，用于指定资源信息
 *
 * @author evol
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityResource {

    /**
     * 资源标识，如: user, role, permission
     */
    String value();

    /**
     * 资源中文名称，如: 用户, 角色, 权限
     */
    String label() default "";
}

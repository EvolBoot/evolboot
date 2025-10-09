package org.evolboot.identity.domain.permission.entity;

/**
 * 权限域
 *
 * @author evol
 */
public enum PermissionScope {

    /**
     * 平台权限（用于平台管理员和员工）
     */
    PLATFORM,

    /**
     * 租户权限（用于租户所有者和员工）
     */
    TENANT
}

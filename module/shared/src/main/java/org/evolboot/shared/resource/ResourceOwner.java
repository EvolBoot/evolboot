package org.evolboot.shared.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.lang.OwnerType;

import java.io.Serializable;

/**
 * 资源归属者
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public class ResourceOwner implements Serializable {

    private OwnerType ownerType;
    private Long ownerId;

    /**
     * 创建用户归属者
     */
    public static ResourceOwner user(Long userId) {
        return new ResourceOwner(OwnerType.USER, userId);
    }

    /**
     * 创建租户归属者
     */
    public static ResourceOwner tenant(Long tenantId) {
        return new ResourceOwner(OwnerType.TENANT, tenantId);
    }

    /**
     * 创建平台归属者
     */
    public static ResourceOwner platform() {
        return new ResourceOwner(OwnerType.PLATFORM, 0L);
    }
}

package org.evolboot.shared.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.shared.lang.UserIdentity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author evol
 */
@Getter
@Setter
@NoArgsConstructor
public class EvolSession implements Serializable {

    private Long userId;

    private String principalName;

    /**
     * 设备信息
     */
    private Map<String, EvolSessionDevice> devices = new HashMap<>();


    /**
     * 权限信息
     */
    private Set<String> authorities;

    /**
     * 租户ID（租户用户有值，平台用户为null）
     */
    private Long tenantId;

    /**
     * 用户身份集合
     */
    private Set<UserIdentity> userIdentities;

    public EvolSession(Long userId, Long tenantId, String principalName) {
        this.userId = userId;
        this.tenantId = tenantId;
        this.principalName = principalName;
    }


    /**
     * 判断是否拥有租户身份
     */
    public boolean hasTenantIdentity() {
        return userIdentities != null && userIdentities.stream()
                .anyMatch(UserIdentity::isTenantIdentity);
    }
}

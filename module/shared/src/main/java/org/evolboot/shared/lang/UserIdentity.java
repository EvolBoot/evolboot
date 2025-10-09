package org.evolboot.shared.lang;

import lombok.Getter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author evol
 */
@Getter
public enum UserIdentity {

    ROLE_SUPER_ADMIN(1),   // 超级管理员
    ROLE_STAFF(2),         // 平台员工
    ROLE_MEMBER(4),        // 会员
    ROLE_TENANT_OWNER(8),  // 租户所有者
    ROLE_TENANT_STAFF(16); // 租户员工


    // 2的次方
    private final int identitySymbol;

    public static final UserIdentity[] VALUES = values();

    public static final Set<UserIdentity> VALUES_SET = new HashSet<>(Arrays.asList(VALUES));


    UserIdentity(int identitySymbol) {
        this.identitySymbol = identitySymbol;
    }

    public static int convertToSymbol(Set<UserIdentity> userIdentities) {
        int identitySymbol = 0;
        for (UserIdentity userIdentity : userIdentities) {
            identitySymbol |= userIdentity.identitySymbol;
        }
        return identitySymbol;
    }

    public static Set<UserIdentity> convertEnum(int identitySymbol) {
        EnumSet<UserIdentity> userIdentities = EnumSet.noneOf(UserIdentity.class);
        for (UserIdentity userIdentity : VALUES) {
            if ((identitySymbol & userIdentity.getIdentitySymbol()) != 0) {
                userIdentities.add(userIdentity);
            }
        }
        return userIdentities;
    }

    /**
     * 判断是否为租户身份
     */
    public boolean isTenantIdentity() {
        return this == ROLE_TENANT_OWNER || this == ROLE_TENANT_STAFF;
    }

    /**
     * 判断是否为平台身份
     */
    public boolean isPlatformIdentity() {
        return this == ROLE_SUPER_ADMIN || this == ROLE_STAFF;
    }


}
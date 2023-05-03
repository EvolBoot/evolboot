package org.evolboot.shared.lang;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author evol
 */
@Getter
public enum UserIdentity {

    ROLE_ADMIN(1), // 管理员
    ROLE_MEMBER(2),
    ROLE_STAFF(4), // 员工
    ROLE_MERCHANT(8); // 商户


    // 2的次方
    private final int identitySymbol;

    private static final UserIdentity[] VALUES = values();


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


}
package org.evolboot.shared.lang;

import lombok.Getter;

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

    UserIdentity(int identitySymbol) {
        this.identitySymbol = identitySymbol;
    }

}
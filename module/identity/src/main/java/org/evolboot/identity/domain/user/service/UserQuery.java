package org.evolboot.identity.domain.user.service;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Query;
import org.evolboot.core.domain.DelStatus;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.shared.lang.UserIdentity;

/**
 * @author evol
 */

@Getter
public class UserQuery extends Query {

    private final Long userId;

    private final String username;

    private final String mobile;

    private final String email;

    private final String registerIp;

    private final DelStatus delStatus;

    private final Long inviterUserId;

    private final UserType userType;

    private UserIdentity userIdentity;

    @Builder
    public UserQuery(Integer page, Integer limit, Long userId, String username, String mobile, String email, String registerIp, DelStatus delStatus, Long inviterUserId, UserType userType, UserIdentity userIdentity) {
        super(page, limit);
        this.userId = userId;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.registerIp = registerIp;
        this.delStatus = delStatus;
        this.inviterUserId = inviterUserId;
        this.userType = userType;
        this.userIdentity = userIdentity;
//        if (ExtendObjects.nonNull(userIdentity)) {
//            this.identitySymbol = userIdentity.getIdentitySymbol();
//        }
    }
}
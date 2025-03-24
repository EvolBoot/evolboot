package org.evolboot.identity.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.entity.DelState;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.shared.lang.UserIdentity;

/**
 * @author evol
 */

@Getter
public class UserQueryRequest extends Query {

    private final Long userId;

    private final String username;

    private final String mobile;

    private final String email;

    private final String registerIp;

    private final DelState delState;

    private final Long inviterUserId;

    private final UserType userType;

    private UserIdentity userIdentity;

    private final Long roleId;

    private final String key;

    @Builder
    public UserQueryRequest(Integer page, Integer limit, Long userId, String username, String mobile, String email, String registerIp, DelState delState, Long inviterUserId, UserType userType, UserIdentity userIdentity, Long roleId, String key, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.userId = userId;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.registerIp = registerIp;
        this.delState = delState;
        this.inviterUserId = inviterUserId;
        this.userType = userType;
        this.userIdentity = userIdentity;
//        if (ExtendObjects.nonNull(userIdentity)) {
//            this.identitySymbol = userIdentity.getIdentitySymbol();
//        }
        this.roleId = roleId;
        this.key = key;
    }
}

package org.evolboot.system.domain.userloginlog.service;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * @author evol
 */
@Getter
public class UserLoginLogQuery extends Query {

    private final String loginIp;
    private final Long userId;

    @Builder
    public UserLoginLogQuery(Integer page, Integer limit, String loginIp, Long userId, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.loginIp = loginIp;
        this.userId = userId;
    }
}

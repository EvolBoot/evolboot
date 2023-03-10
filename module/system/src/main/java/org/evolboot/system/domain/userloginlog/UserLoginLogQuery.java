package org.evolboot.system.domain.userloginlog;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
public class UserLoginLogQuery extends Query {

    private final String loginIp;
    private final Long userId;

    @Builder
    public UserLoginLogQuery(Integer page, Integer limit, String loginIp, Long userId) {
        super(page, limit);
        this.loginIp = loginIp;
        this.userId = userId;
    }
}

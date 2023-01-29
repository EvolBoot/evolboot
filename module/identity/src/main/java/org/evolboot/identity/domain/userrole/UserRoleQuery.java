package org.evolboot.identity.domain.userrole;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class UserRoleQuery extends Query {

    @Builder
    public UserRoleQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

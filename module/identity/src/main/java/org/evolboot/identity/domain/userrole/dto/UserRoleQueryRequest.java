package org.evolboot.identity.domain.userrole.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 用户角色
 *
 * @author evol
 */
@Setter
@Getter
public class UserRoleQueryRequest extends Query {

    @Builder
    public UserRoleQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

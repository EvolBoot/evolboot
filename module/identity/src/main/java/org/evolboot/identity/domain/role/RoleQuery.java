package org.evolboot.identity.domain.role;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;

/**
 * @author evol
 */
@Getter
public class RoleQuery extends Query {

    private String rolename;

    @Builder
    public RoleQuery(Integer page, Integer limit, String rolename) {
        super(page, limit);
        this.rolename = rolename;
    }
}

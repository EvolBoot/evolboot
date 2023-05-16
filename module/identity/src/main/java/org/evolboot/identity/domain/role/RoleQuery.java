package org.evolboot.identity.domain.role;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Query;

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

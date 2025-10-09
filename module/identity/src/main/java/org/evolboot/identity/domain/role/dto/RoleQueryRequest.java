package org.evolboot.identity.domain.role.dto;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.entity.PermissionScope;

import java.util.Date;

/**
 * @author evol
 */
@Getter
public class RoleQueryRequest extends Query {

    private Long id;

    private Date beginAt;

    private Date endAt;

    private final String roleName;

    private final PermissionScope scope;

    private final Long tenantId;

    @Builder
    public RoleQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, String roleName, PermissionScope scope, Long tenantId, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.id = id;
        this.roleName = roleName;
        this.scope = scope;
        this.tenantId = tenantId;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }

}

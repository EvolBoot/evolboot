package org.evolboot.identity.domain.permission.dto;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.permission.entity.Type;

import java.util.Collection;

/**
 * @author evol
 */
@Getter
public class PermissionQueryRequest extends Query {


    private final String title;
    private final String code;
    private final Long parentId;

    private final Type type;
    private final Collection<Long> ids;
    private final PermissionScope scope;

    @Builder
    public PermissionQueryRequest(Integer page, Integer limit, String title, String code, Long parentId, Type type, Collection<Long> ids, PermissionScope scope, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.title = title;
        this.code = code;
        this.parentId = parentId;
        this.type = type;
        this.ids = ids;
        this.scope = scope;
    }
}

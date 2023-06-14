package org.evolboot.identity.domain.permission.service;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.identity.domain.permission.entity.Type;

import java.util.Collection;

/**
 * @author evol
 */
@Getter
public class PermissionQuery extends Query {



    private final String title;
    private final String name;
    private final Long parentId;

    private final Type type ;
    private final Collection<Long> ids;

    @Builder
    public PermissionQuery(Integer page, Integer limit, String title, String name, Long parentId, Type type, Collection<Long> ids, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.title = title;
        this.name = name;
        this.parentId = parentId;
        this.type = type;
        this.ids = ids;
    }
}

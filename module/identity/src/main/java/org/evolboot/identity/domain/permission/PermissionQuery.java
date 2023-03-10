package org.evolboot.identity.domain.permission;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;

/**
 * @author evol
 */
@Getter
public class PermissionQuery extends Query {

    private final String title;
    private final String name;
    private final Long parentId;

    @Builder
    public PermissionQuery(Integer page, Integer limit, String title, String name, Long parentId) {
        super(page, limit);
        this.title = title;
        this.name = name;
        this.parentId = parentId;
    }
}

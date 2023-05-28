package org.evolboot.identity.domain.role.service;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;

/**
 * @author evol
 */
@Getter
public class RoleQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private final String roleName;

    @Builder
    public RoleQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate, String roleName) {
        super(page, limit);
        this.id = id;
        this.roleName = roleName;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }

}

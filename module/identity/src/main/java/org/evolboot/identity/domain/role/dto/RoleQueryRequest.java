package org.evolboot.identity.domain.role.dto;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;

/**
 * @author evol
 */
@Getter
public class RoleQueryRequest extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private final String roleName;

    @Builder
    public RoleQueryRequest(Long id, Integer page, Integer limit, Date startDate, Date endDate, String roleName, String orderField, Direction order) {
        super(page, limit, orderField, order);
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

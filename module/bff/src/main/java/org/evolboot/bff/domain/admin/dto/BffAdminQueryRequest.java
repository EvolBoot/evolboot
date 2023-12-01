package org.evolboot.bff.domain.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * @author evol
 */
@Getter
@Setter
public class BffAdminQueryRequest extends Query {


    @Builder
    public BffAdminQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

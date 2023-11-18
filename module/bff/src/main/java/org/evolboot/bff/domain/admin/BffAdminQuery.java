package org.evolboot.bff.domain.admin;

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
public class BffAdminQuery extends Query {


    @Builder
    public BffAdminQuery(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

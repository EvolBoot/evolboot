package org.evolboot.pay.domain.releasedorder.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 代付订单
 *
 * @author evol
 */
@Setter
@Getter
public class ReleasedOrderQuery extends Query {

    @Builder
    public ReleasedOrderQuery(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

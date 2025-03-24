package org.evolboot.pay.domain.releasedorder.dto;

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
public class ReleasedOrderQueryRequest extends Query {

    @Builder
    public ReleasedOrderQueryRequest(Integer page, Integer limit, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
    }
}

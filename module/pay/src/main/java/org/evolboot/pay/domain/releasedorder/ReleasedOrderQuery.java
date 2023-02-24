package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 代付订单
 *
 * @author evol
 */
@Setter
@Getter
public class ReleasedOrderQuery extends Query {

    @Builder
    public ReleasedOrderQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

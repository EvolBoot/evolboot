package org.evolboot.pay.domain.payoutorder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

@Setter
@Getter
public class PayoutOrderQueryRequest extends Query {

    @Builder
    public PayoutOrderQueryRequest(Integer page, Integer limit, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
    }
}

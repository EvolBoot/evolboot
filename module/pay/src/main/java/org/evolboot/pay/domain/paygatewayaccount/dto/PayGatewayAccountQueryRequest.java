package org.evolboot.pay.domain.paygatewayaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Setter
@Getter
public class PayGatewayAccountQueryRequest extends Query {

    private final Boolean enable;

    @Builder
    public PayGatewayAccountQueryRequest(Integer page, Integer limit, Boolean enable, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.enable = enable;
    }
}

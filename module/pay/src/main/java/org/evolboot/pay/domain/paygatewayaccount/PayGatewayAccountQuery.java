package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Setter
@Getter
public class PayGatewayAccountQuery extends Query {

    private final Boolean enable;

    @Builder
    public PayGatewayAccountQuery(Integer page, Integer limit, Boolean enable) {
        super(page, limit);
        this.enable = enable;
    }
}

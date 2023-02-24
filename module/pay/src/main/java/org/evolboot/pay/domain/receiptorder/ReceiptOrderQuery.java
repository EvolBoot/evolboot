package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Setter
@Getter
public class ReceiptOrderQuery extends Query {

    @Builder
    public ReceiptOrderQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

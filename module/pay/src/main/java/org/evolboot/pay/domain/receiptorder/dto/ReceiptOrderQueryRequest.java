package org.evolboot.pay.domain.receiptorder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Setter
@Getter
public class ReceiptOrderQueryRequest extends Query {


    private String id;

    private Date beginAt;

    private Date endAt;

    @Builder
    public ReceiptOrderQueryRequest(String id, Integer page, Integer limit, Date beginAt, Date endAt, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

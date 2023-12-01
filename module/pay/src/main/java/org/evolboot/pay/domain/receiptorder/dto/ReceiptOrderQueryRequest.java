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

    private Date startDate;

    private Date endDate;

    @Builder
    public ReceiptOrderQueryRequest(String id, Integer page, Integer limit, Date startDate, Date endDate, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

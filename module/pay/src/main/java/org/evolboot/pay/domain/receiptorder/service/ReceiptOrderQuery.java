package org.evolboot.pay.domain.receiptorder.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
public class ReceiptOrderQuery extends Query {


    private String id;

    private Date startDate;

    private Date endDate;

    @Builder
    public ReceiptOrderQuery(String id, Integer page, Integer limit, Date startDate, Date endDate) {
        super(page, limit);
        this.id = id;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

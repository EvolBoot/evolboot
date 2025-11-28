package org.evolboot.pay.domain.payinorder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Setter
@Getter
public class PayinOrderQueryRequest extends Query {


    private String id;

    private LocalDateTime beginAt;

    private LocalDateTime endAt;

    @Builder
    public PayinOrderQueryRequest(String id, Integer page, Integer limit, LocalDateTime beginAt, LocalDateTime endAt, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.id = id;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

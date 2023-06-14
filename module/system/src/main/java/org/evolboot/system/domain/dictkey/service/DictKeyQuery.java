package org.evolboot.system.domain.dictkey.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Setter
@Getter
public class DictKeyQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private final String key;

    @Builder
    public DictKeyQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate, String key, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        this.key = key;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

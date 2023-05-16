package org.evolboot.system.domain.dictvalue;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Setter
@Getter
public class DictValueQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long dictKeyId;

    @Builder
    public DictValueQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate, Long dictKeyId) {
        super(page, limit);
        this.id = id;
        this.dictKeyId = dictKeyId;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

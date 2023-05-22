package org.evolboot.im.domain.groupapply.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Setter
@Getter
public class GroupApplyQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    @Builder
    public GroupApplyQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate) {
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
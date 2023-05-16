package org.evolboot.im.domain.groupmember;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Setter
@Getter
public class GroupMemberQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    @Builder
    public GroupMemberQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate) {
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

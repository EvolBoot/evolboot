package org.evolboot.im.domain.friendapply;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Setter
@Getter
public class FriendApplyQuery extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private  Long toUserId;

    @Builder
    public FriendApplyQuery(Long id, Integer page, Integer limit, Date startDate, Date endDate, Long toUserId) {
        super(page, limit);
        this.id = id;
        this.toUserId = toUserId;
        if (ExtendObjects.nonNull(startDate)) {
            this.startDate = ExtendDateUtil.beginOfDay(startDate);
        }
        if (ExtendObjects.nonNull(endDate)) {
            this.endDate = ExtendDateUtil.endOfDay(endDate);
        }
    }
}

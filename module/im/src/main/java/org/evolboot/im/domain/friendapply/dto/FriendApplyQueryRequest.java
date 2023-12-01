package org.evolboot.im.domain.friendapply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
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
public class FriendApplyQueryRequest extends Query {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long toUserId;

    @Builder
    public FriendApplyQueryRequest(Long id, Integer page, Integer limit, Date startDate, Date endDate, Long toUserId, String orderField, Direction order) {
        super(page, limit, orderField, order);
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

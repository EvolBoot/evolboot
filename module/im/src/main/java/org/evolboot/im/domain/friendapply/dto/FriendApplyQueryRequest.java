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

    private Date beginAt;

    private Date endAt;

    private Long toUserId;

    @Builder
    public FriendApplyQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, Long toUserId, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        this.toUserId = toUserId;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

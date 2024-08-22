package org.evolboot.im.domain.groupmember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
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
public class GroupMemberQueryRequest extends Query {

    private Long id;

    private Date beginAt;

    private Date endAt;

    @Builder
    public GroupMemberQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

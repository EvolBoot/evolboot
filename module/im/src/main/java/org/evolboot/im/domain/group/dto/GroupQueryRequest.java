package org.evolboot.im.domain.group.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;

import java.util.Date;


/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Setter
@Getter
public class GroupQueryRequest extends Query {

    private Long id;

    private Date beginAt;

    private Date endAt;

    @Builder
    public GroupQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, String sortField, Direction direction) {
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

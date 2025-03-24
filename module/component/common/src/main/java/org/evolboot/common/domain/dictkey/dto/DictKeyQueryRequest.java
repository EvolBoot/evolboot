package org.evolboot.common.domain.dictkey.dto;

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
public class DictKeyQueryRequest extends Query {

    private Long id;

    private Date beginAt;

    private Date endAt;

    private final String key;

    @Builder
    public DictKeyQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, String key, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.id = id;
        this.key = key;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

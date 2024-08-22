package org.evolboot.common.domain.dictvalue.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
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
public class DictValueQueryRequest extends Query {

    private Long id;

    private Date beginAt;

    private Date endAt;

    private Long dictKeyId;

    private final String key;

    @Builder
    public DictValueQueryRequest(Long id, Integer page, Integer limit, Date beginAt, Date endAt, Long dictKeyId, String key, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.id = id;
        this.dictKeyId = dictKeyId;
        this.key = key;
        if (ExtendObjects.nonNull(beginAt)) {
            this.beginAt = ExtendDateUtil.beginOfDay(beginAt);
        }
        if (ExtendObjects.nonNull(endAt)) {
            this.endAt = ExtendDateUtil.endOfDay(endAt);
        }
    }
}

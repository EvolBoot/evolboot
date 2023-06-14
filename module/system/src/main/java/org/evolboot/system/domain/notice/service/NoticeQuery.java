package org.evolboot.system.domain.notice.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 公告
 *
 * @author evol
 */
@Setter
@Getter
public class NoticeQuery extends Query {

    private final Boolean enable;

    @Builder
    public NoticeQuery(Integer page, Integer limit, Boolean enable, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.enable = enable;
    }
}

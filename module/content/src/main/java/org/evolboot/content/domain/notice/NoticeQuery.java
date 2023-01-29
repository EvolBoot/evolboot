package org.evolboot.content.domain.notice;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class NoticeQuery extends Query {

    private final Boolean enable;

    @Builder
    public NoticeQuery(Integer page, Integer limit, Boolean enable) {
        super(page, limit);
        this.enable = enable;
    }
}

package org.evolboot.system.domain.news.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 新闻
 *
 * @author evol
 */
@Setter
@Getter
public class NewsQuery extends Query {

    private final Boolean show;

    @Builder
    public NewsQuery(Integer page, Integer limit, Boolean show, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.show = show;
    }
}

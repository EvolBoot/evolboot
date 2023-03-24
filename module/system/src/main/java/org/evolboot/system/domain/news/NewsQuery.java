package org.evolboot.system.domain.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;

/**
 * 新闻
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class NewsQuery extends Query {

    private final Boolean show;

    @Builder
    public NewsQuery(Integer page, Integer limit, Boolean show) {
        super(page, limit);
        this.show = show;
    }
}

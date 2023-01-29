package org.evolboot.content.domain.news;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

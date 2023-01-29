package org.evolboot.content.domain.banner;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * banner
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class BannerQuery extends Query {

    private final Boolean show;

    @Builder
    public BannerQuery(Integer page, Integer limit, Boolean show) {
        super(page, limit);
        this.show = show;
    }
}

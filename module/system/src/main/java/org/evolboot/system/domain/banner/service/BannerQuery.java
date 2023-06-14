package org.evolboot.system.domain.banner.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * banner
 *
 * @author evol
 */
@Setter
@Getter
public class BannerQuery extends Query {

    private final Boolean show;

    @Builder
    public BannerQuery(Integer page, Integer limit, Boolean show, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.show = show;
    }
}

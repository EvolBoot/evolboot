package org.evolboot.locale.domain.region;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public class RegionQuery extends Query {

    @Builder
    public RegionQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

package org.evolboot.bffapp.domain;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 *
 */
@Getter
@Setter
public class BffAppQuery extends Query {

    @Builder
    public BffAppQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

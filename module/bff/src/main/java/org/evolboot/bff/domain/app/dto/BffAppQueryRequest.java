package org.evolboot.bff.domain.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * @author evol
 */
@Getter
@Setter
public class BffAppQueryRequest extends Query {

    @Builder
    public BffAppQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

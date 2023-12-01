package org.evolboot.identity.domain.bff.dto;

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
public class IdentityBffQueryRequest extends Query {

    @Builder
    public IdentityBffQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

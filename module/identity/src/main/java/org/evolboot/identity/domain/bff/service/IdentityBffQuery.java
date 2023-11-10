package org.evolboot.identity.domain.bff.service;

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
public class IdentityBffQuery extends Query {

    @Builder
    public IdentityBffQuery(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

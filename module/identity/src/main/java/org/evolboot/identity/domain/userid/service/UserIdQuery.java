package org.evolboot.identity.domain.userid.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * UserId
 *
 * @author evol
 */
@Setter
@Getter
public class UserIdQuery extends Query {

    @Builder
    public UserIdQuery(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

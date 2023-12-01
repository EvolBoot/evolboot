package org.evolboot.identity.domain.userid.dto;

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
public class UserIdQueryRequest extends Query {

    @Builder
    public UserIdQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

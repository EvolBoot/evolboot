package org.evolboot.identity.domain.userid;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class UserIdQuery extends Query {

    @Builder
    public UserIdQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

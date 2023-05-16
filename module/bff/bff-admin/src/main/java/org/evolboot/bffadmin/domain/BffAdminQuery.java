package org.evolboot.bffadmin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;

/**
 * @author evol
 */
@Getter
@Setter
public class BffAdminQuery extends Query {


    @Builder
    public BffAdminQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

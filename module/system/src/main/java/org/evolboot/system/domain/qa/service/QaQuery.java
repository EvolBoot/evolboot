package org.evolboot.system.domain.qa.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * QA
 *
 * @author evol
 */
@Setter
@Getter
public class QaQuery extends Query {

    @Builder
    public QaQuery(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}

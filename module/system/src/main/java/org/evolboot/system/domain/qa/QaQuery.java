package org.evolboot.system.domain.qa;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
    public QaQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

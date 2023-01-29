package org.evolboot.content.domain.qa;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * QA
 *
 * @author evol
 * 
 */
@Setter
@Getter
public class QaQuery extends Query {

    @Builder
    public QaQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}

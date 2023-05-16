package org.evolboot.identity.domain.user.relation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;
import org.evolboot.shared.lang.SqlOperator;

/**
 * 用户上下级关系
 *
 * @author evol
 */
@Setter
@Getter
public class RelationQuery extends Query {

    private final SqlOperator distanceOperator;
    private final Integer distance;
    private final Long descendant;
    private final Long ancestor;


    @Builder
    public RelationQuery(Integer page, Integer limit, SqlOperator distanceOperator, Integer distance, Long descendant, Long ancestor) {
        super(page, limit);
        this.distanceOperator = distanceOperator;
        this.distance = distance;
        this.descendant = descendant;
        this.ancestor = ancestor;
    }
}

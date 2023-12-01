package org.evolboot.identity.domain.user.relation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.shared.lang.SqlOperator;

/**
 * 用户上下级关系
 *
 * @author evol
 */
@Setter
@Getter
public class RelationQueryRequest extends Query {

    private final SqlOperator distanceOperator;
    private final Integer distance;
    private final Long descendant;
    private final Long ancestor;


    @Builder
    public RelationQueryRequest(Integer page, Integer limit, SqlOperator distanceOperator, Integer distance, Long descendant, Long ancestor, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.distanceOperator = distanceOperator;
        this.distance = distance;
        this.descendant = descendant;
        this.ancestor = ancestor;
    }
}

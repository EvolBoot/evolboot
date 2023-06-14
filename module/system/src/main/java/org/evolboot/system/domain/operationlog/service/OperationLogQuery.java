package org.evolboot.system.domain.operationlog.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

import java.util.Date;

/**
 * @author evol
 */
@Setter
@Getter
public class OperationLogQuery extends Query {

    private final Date end;
    private final Date begin;
    private final Long userId;
    private final String operation;

    @Builder
    public OperationLogQuery(Integer page, Integer limit, Date end, Date begin, Long userId, String operation, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.end = end;
        this.begin = begin;
        this.userId = userId;
        this.operation = operation;
    }
}

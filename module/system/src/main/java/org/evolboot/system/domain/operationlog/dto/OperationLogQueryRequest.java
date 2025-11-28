package org.evolboot.system.domain.operationlog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author evol
 */
@Setter
@Getter
public class OperationLogQueryRequest extends Query {

    private final LocalDateTime end;
    private final LocalDateTime begin;
    private final Long userId;
    private final String operation;

    @Builder
    public OperationLogQueryRequest(Integer page, Integer limit, LocalDateTime end, LocalDateTime begin, Long userId, String operation, String sortField, Direction direction) {
        super(page, limit, sortField, direction);
        this.end = end;
        this.begin = begin;
        this.userId = userId;
        this.operation = operation;
    }
}

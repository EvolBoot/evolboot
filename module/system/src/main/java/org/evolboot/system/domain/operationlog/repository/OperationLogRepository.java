package org.evolboot.system.domain.operationlog.repository;


import org.evolboot.core.data.Page;
import org.evolboot.system.domain.operationlog.OperationLog;
import org.evolboot.system.domain.operationlog.OperationLogQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface OperationLogRepository {
    OperationLog save(OperationLog operationLog);

    Optional<OperationLog> findById(Long id);

    Page<OperationLog> page(OperationLogQuery query);

    void deleteById(Long id);

    List<OperationLog> findAll();

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<OperationLog> findOne(OperationLogQuery query);

}

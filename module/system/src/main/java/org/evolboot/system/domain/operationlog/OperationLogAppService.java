package org.evolboot.system.domain.operationlog;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.operationlog.entity.OperationLog;
import org.evolboot.system.domain.operationlog.service.OperationLogQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface OperationLogAppService {

    void create(OperationLog operationLog);

    List<OperationLog> findAll();

    Page<OperationLog> page(OperationLogQuery query);

    OperationLog findById(Long id);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<OperationLog> findOne(OperationLogQuery query);


}


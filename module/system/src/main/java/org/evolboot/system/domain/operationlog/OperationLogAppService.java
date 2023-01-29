package org.evolboot.system.domain.operationlog;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * @author evol
 */
public interface OperationLogAppService {

    void create(OperationLog operationLog);

    List<OperationLog> findAll();

    Page<OperationLog> page(OperationLogQuery query);

    OperationLog findById(Long id);
}


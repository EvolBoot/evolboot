package org.evolboot.system.domain.operationlog;

import org.evolboot.system.domain.operationlog.repository.OperationLogRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class OperationLogCreateFactory {

    private final OperationLogRepository repository;

    public OperationLogCreateFactory(OperationLogRepository repository) {
        this.repository = repository;
    }

    public void create(OperationLog operationLog) {
        repository.save(operationLog);
    }
}

package org.evolboot.system.domain.operationlog;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.operationlog.repository.OperationLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.operationlog.service.OperationLogCreateFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultOperationLogAppService implements OperationLogAppService {

    private final OperationLogCreateFactory factory;
    private final OperationLogRepository repository;

    public DefaultOperationLogAppService(OperationLogCreateFactory factory, OperationLogRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Transactional
    @Override
    public void create(OperationLog operationLog) {

        factory.create(operationLog);
    }


    @Override
    public List<OperationLog> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<OperationLog> page(OperationLogQuery query) {
        return repository.page(query);
    }

    @Override
    public OperationLog findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}

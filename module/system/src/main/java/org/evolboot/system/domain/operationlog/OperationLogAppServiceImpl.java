package org.evolboot.system.domain.operationlog;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.operationlog.entity.OperationLog;
import org.evolboot.system.domain.operationlog.repository.OperationLogRepository;
import org.evolboot.system.domain.operationlog.service.OperationLogCreateFactory;
import org.evolboot.system.domain.operationlog.dto.OperationLogQueryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
@Service
@Slf4j
public class OperationLogAppServiceImpl implements OperationLogAppService {

    private final OperationLogCreateFactory factory;
    private final OperationLogRepository repository;

    public OperationLogAppServiceImpl(OperationLogCreateFactory factory, OperationLogRepository repository) {
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
    public Page<OperationLog> page(OperationLogQueryRequest query) {
        return repository.page(query);
    }

    @Override
    public OperationLog findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public Optional<OperationLog> findOne(OperationLogQueryRequest query) {
        return repository.findOne(query);
    }
}

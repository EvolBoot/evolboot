package org.evolboot.system.domain.operationlog.repository;


import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.operationlog.entity.OperationLog;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface OperationLogRepository extends BaseRepository<OperationLog, Long> {
    OperationLog save(OperationLog operationLog);

    Optional<OperationLog> findById(Long id);

    void deleteById(Long id);

    List<OperationLog> findAll();

    <Q extends Query> List<OperationLog> findAll(Q query);

    <Q extends Query> Optional<OperationLog> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<OperationLog> page(Q query);
}

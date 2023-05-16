package org.evolboot.mq.core.domain.mqtransaction.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.core.domain.mqtransaction.repository.MqTransactionRepository;

/**
 * Mq事务
 *
 * @author evol
 */
@Slf4j
public abstract class MqTransactionSupportService {

    protected final MqTransactionRepository repository;

    protected MqTransactionSupportService(MqTransactionRepository repository) {
        this.repository = repository;
    }


}

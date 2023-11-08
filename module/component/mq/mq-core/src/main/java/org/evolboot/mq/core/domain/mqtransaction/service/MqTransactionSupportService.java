package org.evolboot.mq.core.domain.mqtransaction.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.core.domain.mqtransaction.repository.MqTransactionRepository;
import org.springframework.stereotype.Service;

/**
 * Mq事务
 *
 * @author evol
 */
@Slf4j
@Service
public class MqTransactionSupportService {

    protected final MqTransactionRepository repository;

    protected MqTransactionSupportService(MqTransactionRepository repository) {
        this.repository = repository;
    }


}

package org.evolboot.mq.core.domain.mqtransaction.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.core.domain.mqtransaction.entity.MqTransaction;
import org.evolboot.mq.core.domain.mqtransaction.repository.MqTransactionRepository;
import org.springframework.stereotype.Service;

/**
 * Mq事务
 *
 * @author evol
 */
@Slf4j
@Service
public class MqTransactionCreateFactory {

    private final MqTransactionRepository repository;

    private final MqTransactionSupportService supportService;

    protected MqTransactionCreateFactory(MqTransactionRepository repository, MqTransactionSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


    public MqTransaction execute(String remark) {
        MqTransaction mqTransaction = new MqTransaction(remark);
        repository.save(mqTransaction);
        return mqTransaction;
    }


}

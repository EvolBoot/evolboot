package org.evolboot.mq.producer.mqtransaction.service;

import org.evolboot.mq.producer.mqtransaction.MqTransaction;
import org.evolboot.mq.producer.mqtransaction.repository.MqTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Mq事务
 *
 * @author evol
 */
@Slf4j
@Service
public class MqTransactionCreateFactory extends MqTransactionSupportService {
    protected MqTransactionCreateFactory(MqTransactionRepository repository) {
        super(repository);
    }


    public MqTransaction execute(String remark) {
        MqTransaction mqTransaction = new MqTransaction(remark);
        repository.save(mqTransaction);
        return mqTransaction;
    }


}

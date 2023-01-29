package org.evolboot.mq.producer.mqtransaction.repository;

import org.evolboot.mq.producer.mqtransaction.MqTransaction;

import java.util.List;
import java.util.Optional;

/**
 * Mq事务
 *
 * @author evol
 */
public interface MqTransactionRepository {

    MqTransaction save(MqTransaction mqTransaction);

    Optional<MqTransaction> findById(Long id);

    void deleteById(Long id);

    List<MqTransaction> findAll();
}

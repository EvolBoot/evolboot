package org.evolboot.mq.core.domain.mqtransaction.repository.jpa;

import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.mq.core.domain.mqtransaction.MqTransaction;
import org.evolboot.mq.core.domain.mqtransaction.repository.MqTransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Mq事务
 *
 * @author evol
 */
@Repository
public interface JpaMqTransactionRepository extends MqTransactionRepository, ExtendedQuerydslPredicateExecutor<MqTransaction>, JpaRepository<MqTransaction, Long> {


}
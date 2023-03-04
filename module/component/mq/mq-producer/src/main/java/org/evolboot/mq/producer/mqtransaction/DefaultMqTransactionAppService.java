package org.evolboot.mq.producer.mqtransaction;

import org.evolboot.mq.producer.mqtransaction.repository.MqTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.mq.producer.mqtransaction.service.MqTransactionCreateFactory;
import org.evolboot.mq.producer.mqtransaction.service.MqTransactionSupportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Mq事务
 *
 * @author evol
 */
@Slf4j
@Service
public class DefaultMqTransactionAppService extends MqTransactionSupportService implements MqTransactionAppService {


    private final MqTransactionCreateFactory factory;


    protected DefaultMqTransactionAppService(MqTransactionRepository repository, MqTransactionCreateFactory factory) {
        super(repository);
        this.factory = factory;
    }

    @Override
    public Optional<MqTransaction> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public MqTransaction create() {
        return factory.execute(null);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public MqTransaction create(String remark) {
        return factory.execute(remark);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<MqTransaction> findAll() {
        return repository.findAll();
    }


}

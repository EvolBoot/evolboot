package org.evolboot.mq.core.domain.mqtransaction;

import java.util.List;
import java.util.Optional;

/**
 * Mq事务
 *
 * @author evol
 */
public interface MqTransactionAppService {

    Optional<MqTransaction> findById(Long id);

    MqTransaction create();

    MqTransaction create(String remark);

    void delete(Long id);

    List<MqTransaction> findAll();

    boolean existsById(Long id);

}

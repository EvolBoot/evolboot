package org.evolboot.mq.producer.mqtransaction;

import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Mq事务
 *
 * @author evol
 */
@Table(name = "evol_mq_producer_mq_transaction")
@Getter
@Slf4j
@Entity
public class MqTransaction extends AbstractEntity<Long> implements AggregateRoot<MqTransaction> {

    @Id
    private Long id;

    protected Date createTime = new Date();

    private String remark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public MqTransaction() {
        generateId();
    }

    public MqTransaction(String remark) {
        generateId();
        this.remark = remark;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public MqTransaction root() {
        return this;
    }
}

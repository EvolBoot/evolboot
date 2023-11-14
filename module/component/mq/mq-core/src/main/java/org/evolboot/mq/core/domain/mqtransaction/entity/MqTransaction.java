package org.evolboot.mq.core.domain.mqtransaction.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


/**
 * Mq事务
 *
 * @author evol
 */
@Table(name = "evoltb_mq_core_mq_transaction")
@Getter
@Slf4j
@Entity
public class MqTransaction extends AbstractEntity<Long> implements AggregateRoot<MqTransaction> {

    @Id
    private Long id;

    protected Date createAt = new Date();

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

package org.evolboot.core.data.jpa;

import lombok.Getter;
import org.evolboot.core.entity.AbstractEntity;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @author evol
 */
@Scope("prototype")
@MappedSuperclass
public abstract class JpaAbstractEntity<ID extends Serializable> extends AbstractEntity<ID> {

    @Version
    protected Long version;

    @Getter
    @CreationTimestamp
    protected Date createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updateAt;


}

package org.evolboot.core.data.jpa;

import org.evolboot.core.domain.AbstractEntity;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
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
    protected Date createTime = new Date();

    @UpdateTimestamp
    @Column(nullable = false)
    private Date lastModifyTime;


}

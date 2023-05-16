package org.evolboot.core.domain;

import lombok.Getter;
import org.evolboot.core.util.IdUtil;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author evol
 */
@Embeddable
@Getter
public class LongIdentity implements Serializable {

    @Column(name = "id")
    private Long value = IdUtil.nextId();

    public LongIdentity() {
    }


    public LongIdentity(Long value) {
        this.value = value;
    }


}

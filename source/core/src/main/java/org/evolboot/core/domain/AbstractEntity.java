package org.evolboot.core.domain;

import org.springframework.context.annotation.Scope;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author evol
 * 
 */
@Scope("prototype")
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    public abstract ID id();


}

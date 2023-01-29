package org.evolboot.core.domain;

/**
 * @author evol
 * 
 */
public interface AggregateRoot<R extends AbstractEntity> {
    R root();
}

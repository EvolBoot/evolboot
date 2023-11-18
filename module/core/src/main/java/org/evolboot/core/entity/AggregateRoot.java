package org.evolboot.core.entity;

/**
 * @author evol
 */
public interface AggregateRoot<R extends AbstractEntity> {
    R root();
}

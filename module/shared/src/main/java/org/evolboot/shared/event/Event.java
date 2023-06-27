package org.evolboot.shared.event;


import java.io.Serializable;

/**
 * @author evol
 */

public interface Event<T> extends Serializable {

    T getEventSourceId();

}
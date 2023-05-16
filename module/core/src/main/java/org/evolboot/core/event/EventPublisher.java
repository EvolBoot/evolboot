package org.evolboot.core.event;


import org.evolboot.shared.event.Event;

/**
 * 事件发布
 *
 * @author evol
 */
public interface EventPublisher {

    void publishEvent(Event event);

}

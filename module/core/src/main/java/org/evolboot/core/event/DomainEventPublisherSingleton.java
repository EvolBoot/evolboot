package org.evolboot.core.event;

import org.evolboot.shared.event.Event;

/**
 * @author evol
 * 
 */
public final class DomainEventPublisherSingleton {

    private EventPublisher eventPublisher;

    private static DomainEventPublisherSingleton instance;

    private DomainEventPublisherSingleton(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public static DomainEventPublisherSingleton newInstance(EventPublisher eventPublisher) {
//        DefaultMessageAssert.notNull("eventPublisher", eventPublisher);
        if (instance == null) {
            instance = new DomainEventPublisherSingleton(eventPublisher);
        }
        return instance;
    }

    public static void publishEvent(Event event) {
        instance.eventPublisher.publishEvent(event);
    }

}

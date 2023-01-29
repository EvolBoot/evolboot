package org.evolboot.core.event;

/**
 * @author evol
 */
public abstract class EventConfig {

    public static void config(EventPublisher eventPublisher) {
        DomainEventPublisherSingleton.newInstance(eventPublisher);
    }

}

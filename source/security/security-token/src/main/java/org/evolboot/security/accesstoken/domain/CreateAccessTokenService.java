package org.evolboot.security.accesstoken.domain;

import org.evolboot.core.event.EventPublisher;

/**
 * @author evol

 */
public class CreateAccessTokenService {


    private final EventPublisher eventPublisher;


    public CreateAccessTokenService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}

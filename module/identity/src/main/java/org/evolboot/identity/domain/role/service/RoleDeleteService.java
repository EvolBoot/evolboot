package org.evolboot.identity.domain.role.service;

import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.role.Role;
import org.evolboot.shared.event.role.RoleDeleteEvent;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 *
 */
@Service
public class RoleDeleteService {

    private final RoleRepository repository;
    private final EventPublisher eventPublisher;

    public RoleDeleteService(RoleRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(Long roleId) {
        Assert.notNull(roleId, IdentityI18nMessage.Role.idNotNull());
        Role role = repository.findById(roleId).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.Role.roleNotFound()));
        repository.deleteById(roleId);
        eventPublisher.publishEvent(new RoleDeleteEvent(role.id()));
    }
}

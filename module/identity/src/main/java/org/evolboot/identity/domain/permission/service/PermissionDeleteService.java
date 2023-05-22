package org.evolboot.identity.domain.permission.service;


import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.shared.event.permission.PermissionDeleteEvent;
import org.springframework.stereotype.Service;

import static org.evolboot.identity.IdentityI18nMessage.Permission.idNotNull;
import static org.evolboot.identity.IdentityI18nMessage.Permission.notFound;

/**
 *
 */
@Service
public class PermissionDeleteService {

    private final PermissionRepository repository;
    private final EventPublisher eventPublisher;

    public PermissionDeleteService(PermissionRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(Long permissionId) {
        Assert.notNull(permissionId, idNotNull());
        Permission permission = repository.findById(permissionId).orElseThrow(() -> new DomainNotFoundException(notFound()));
        repository.deleteById(permissionId);
        eventPublisher.publishEvent(new PermissionDeleteEvent(permission.id()));
    }


}

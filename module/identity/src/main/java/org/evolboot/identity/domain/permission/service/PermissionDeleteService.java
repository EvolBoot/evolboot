package org.evolboot.identity.domain.permission.service;


import org.evolboot.core.event.EventPublisher;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.evolboot.shared.event.permission.PermissionDeleteEvent;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.evolboot.identity.IdentityI18nMessage.Permission.idNotNull;

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
        // 查下级
        List<Long> deleteIds = repository.findChildren(permissionId);
        // 加自己
        deleteIds.add(permissionId);
        // 全删除
        repository.deleteAllByIdInBatch(deleteIds);
        // 通知
        eventPublisher.publishEvent(new PermissionDeleteEvent(deleteIds));
    }


    public void batchDelete(List<Long> ids) {
        ids.forEach(this::execute);
    }
}

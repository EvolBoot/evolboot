package org.evolboot.identity.domain.permission.service;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class PermissionUpdateService {

    private final PermissionRepository repository;
    private final CheckParentIdsService checkParentIdsService;


    public PermissionUpdateService(PermissionRepository repository, CheckParentIdsService checkParentIdsService) {
        this.repository = repository;
        this.checkParentIdsService = checkParentIdsService;
    }

    public Permission update(Request request) {
        checkParentIdsService.parentIdExist(request.getParentIds());
        Assert.notNull(request.getId(), IdentityI18nMessage.Permission.idNotNull());
        Permission permission = repository.findById(request.getId()).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.Permission.notFound()));
        permission.setName(request.getName());
        permission.setPath(request.getPath());
        permission.setType(request.getType());
        permission.setSort(request.getSort());
        permission.setPath(request.getPath());
        permission.setMeta(request.getMeta());
        permission.setIsLink(request.getIsLink());
        permission.setParentIds(request.getParentIds());
        permission.setComponent(request.getComponent());
        repository.save(permission);
        return permission;
    }

    @Getter
    @Setter
    public static class Request extends PermissionRequestBase {
        private Long id;
    }
}

package org.evolboot.identity.domain.permission.service;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.identity.domain.permission.PermissionRequestBase;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


/**
 */
@Service
public class PermissionUpdateService {

    private final PermissionRepository repository;

    public PermissionUpdateService(PermissionRepository repository) {
        this.repository = repository;
    }

    public Permission update(Long id, Request request) {
        Assert.notNull(id, IdentityI18nMessage.Permission.idNotNull());
        Permission permission = repository.findById(id).orElseThrow(() -> new DomainNotFoundException(IdentityI18nMessage.Permission.notFound()));
        permission
                .setTitle(request.getTitle())
                .setPerm(request.getPerm())
                .setPath(request.getPath())
                .setType(request.getType())
                .setSort(request.getSort())
                .setIcon(request.getIcon())
                .setPath(request.getPath())
                .setRemark(request.getRemark());
        repository.save(permission);
        return permission;
    }

    @Getter
    @Setter
    public static class Request extends PermissionRequestBase {


    }
}

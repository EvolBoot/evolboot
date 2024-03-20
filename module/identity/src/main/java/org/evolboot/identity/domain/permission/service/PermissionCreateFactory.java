package org.evolboot.identity.domain.permission.service;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.permission.dto.PermissionRequestBase;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 */
@Service
public class PermissionCreateFactory {

    private final CheckParentIdsService checkParentIdsService;

    private final PermissionRepository repository;


    public PermissionCreateFactory(CheckParentIdsService checkParentIdsService, PermissionRepository repository) {
        this.checkParentIdsService = checkParentIdsService;
        this.repository = repository;
    }

    public Permission create(Request request) {
        Optional<Permission> parent = repository.findById(request.getParentId());
        Assert.isTrue(parent.isPresent(), "上级权限不存在");

        Permission permission = Permission.builder()
                .parentIds(parent.get().convertToParentIds())
                .component(request.getComponent())
                .name(request.getName())
                .type(request.getType())
                .sort(request.getSort())
                .path(request.getPath())
                .meta(request.getMeta())
                .build();
        repository.save(permission);
        return permission;
    }

    @Getter
    @Setter
    public static class Request extends PermissionRequestBase {

    }

}

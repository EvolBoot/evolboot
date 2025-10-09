package org.evolboot.identity.domain.permission.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.compress.utils.Lists;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.domain.permission.dto.PermissionRequestBase;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<Long> parentIds = Lists.newArrayList();
        if (request.getParentId() != null && request.getParentId() > 0) {
            Optional<Permission> parent = repository.findById(request.getParentId());
            Assert.isTrue(parent.isPresent(), "上级权限不存在");
            parentIds = parent.get().convertToParentIds();
        }

        Permission permission = Permission.builder()
                .parentIds(parentIds)
                .component(request.getComponent())
                .code(request.getCode())
                .type(request.getType())
                .sort(request.getSort())
                .path(request.getPath())
                .meta(request.getMeta())
                .scope(request.getScope())
                .build();
        repository.save(permission);
        return permission;
    }

    @Getter
    @Setter
    public static class Request extends PermissionRequestBase {

    }

}

package org.evolboot.identity.domain.permission;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.permission.repository.PermissionRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class PermissionCreateFactory {

    private final PermissionRepository repository;

    public PermissionCreateFactory(PermissionRepository repository) {
        this.repository = repository;
    }

    public Permission create(Request request) {
        if (ExtendObjects.nonNull(request.getParentId()) && request.getParentId() != Permission.DEFAULT_PERMISSION_PARENT) {
            Assert.isTrue(repository.findById(request.getParentId()).isPresent(), "不存在的父类ID");
        }
        Permission permission = Permission.builder()
                .parentId(request.getParentId())
                .title(request.getTitle())
                .perm(request.getPerm())
                .type(request.getType())
                .sort(request.getSort())
                .path(request.getPath())
                .remark(request.getRemark())
                .icon(request.getIcon())
                .build();
        repository.save(permission);
        return permission;
    }

    @Getter
    @Setter
    public static class Request extends PermissionRequestBase {
        @Schema(description = "上级", example = Permission.DEFAULT_PERMISSION_PARENT + "")
        private Long parentId;

    }

}

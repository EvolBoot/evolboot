package org.evolboot.identity.remote.userrole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.identity.remote.user.dto.UserRoleUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.HAS_ROLE_UPDATE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_OWNER;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * Tenant User Role Resource
 * 租户用户角色管理 - 租户所有者和租户员工可访问
 * 管理租户员工的角色分配
 */
@Slf4j
@RestController
@RequestMapping("/tenant/v1")
@Tag(name = "租户用户角色管理", description = "租户用户角色管理")
@AdminClient
public class TenantUserRoleResourceV1 {

    private final UserRoleAppService service;

    public TenantUserRoleResourceV1(UserRoleAppService service) {
        this.service = service;
    }

    /**
     * 修改租户员工角色
     */
    @Operation(summary = "修改租户员工角色")
    @OperationLog("修改租户员工角色")
    @PutMapping("/users/{id}/role/update")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_ROLE_UPDATE)
    public ResponseModel<?> updateRole(
            @PathVariable("id") Long id,
            @RequestBody @Valid UserRoleUpdateRequest request
    ) {
        // 只能修改本租户员工的角色
        service.updateRole(id, request.getRoles());
        return ResponseModel.ok();
    }

}

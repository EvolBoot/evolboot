package org.evolboot.identity.remote.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.dto.RoleQueryRequest;
import org.evolboot.identity.remote.role.dto.CreateRoleRequest;
import org.evolboot.identity.remote.role.dto.UpdateRoleRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.Role.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_OWNER;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_STAFF;

/**
 * Tenant Role Resource
 * 租户角色管理 - 租户所有者和租户员工可访问
 * 只能管理本租户的角色，数据自动隔离
 */
@RestController
@RequestMapping("/v1/tenant/roles")
@Tag(name = "租户角色管理", description = "租户角色管理")
@AdminClient
public class TenantRoleResourceV1 {

    private final RoleAppService service;

    public TenantRoleResourceV1(RoleAppService service) {
        this.service = service;
    }

    /**
     * 创建角色
     * 自动设置 scope=TENANT 和当前租户ID
     */
    @PostMapping
    @Operation(summary = "创建角色")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_CREATE)
    public ResponseModel create(@Valid @RequestBody CreateRoleRequest request) {
        service.create(request.toRequest());
        return ResponseModel.ok();
    }

    /**
     * 修改角色
     */
    @PutMapping("")
    @Operation(summary = "修改角色")
    @OperationLog("修改角色")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_UPDATE)
    public ResponseModel<?> update(@Valid @RequestBody UpdateRoleRequest request) {
        service.update(request);
        return ResponseModel.ok();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    @OperationLog("删除角色")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 获取单个角色
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取单个角色")
    @OperationLog("获取单个角色")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PAGE)
    public ResponseModel<?> get(@PathVariable("id") Long id) {
        return ResponseModel.ok(service.findById(id));
    }

    /**
     * 角色列表
     * 自动过滤本租户的角色
     */
    @GetMapping
    @Operation(summary = "角色列表")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PAGE)
    public ResponseModel<Page<Role>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction,
            @RequestParam(required = false) String roleName
    ) {
        // 自动获取当前租户ID，强制数据隔离
        Long tenantId = SecurityAccessTokenHolder.getTenantId();

        RoleQueryRequest query = RoleQueryRequest.builder()
                .roleName(roleName)
                .page(page)
                .limit(limit)
                .direction(direction)
                .sortField(sortField)
                .scope(PermissionScope.TENANT)  // 强制只查询 TENANT 角色
                .tenantId(tenantId)  // 强制只查询本租户的角色
                .build();
        return ResponseModel.ok(service.page(query));
    }
}

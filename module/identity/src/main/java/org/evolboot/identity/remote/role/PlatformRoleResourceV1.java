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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.Role.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_STAFF;

/**
 * Platform Role Resource
 * 平台角色管理 - 超管和员工可访问
 * 可以管理平台角色(scope=PLATFORM)，也可以管理所有租户的角色
 */
@RestController
@RequestMapping("/v1/platform/roles")
@Tag(name = "平台角色管理", description = "平台角色管理")
@AdminClient
public class PlatformRoleResourceV1 {

    private final RoleAppService service;

    public PlatformRoleResourceV1(RoleAppService service) {
        this.service = service;
    }

    /**
     * 创建角色
     */
    @PostMapping
    @Operation(summary = "创建角色")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
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
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
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
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
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
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<?> get(@PathVariable("id") Long id) {
        return ResponseModel.ok(service.findById(id));
    }

    /**
     * 角色列表
     * 支持查询 PLATFORM 角色和所有租户的角色
     * 可通过 scope 和 tenantId 参数进行过滤
     */
    @GetMapping
    @Operation(summary = "角色列表")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Role>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction,
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) PermissionScope scope,
            @RequestParam(required = false) Long tenantId
    ) {
        RoleQueryRequest query = RoleQueryRequest.builder()
                .roleName(roleName)
                .page(page)
                .limit(limit)
                .direction(direction)
                .sortField(sortField)
                .scope(scope)
                .tenantId(tenantId)
                .build();
        return ResponseModel.ok(service.page(query));
    }
}

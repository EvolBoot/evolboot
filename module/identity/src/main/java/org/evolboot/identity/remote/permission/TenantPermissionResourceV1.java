package org.evolboot.identity.remote.permission;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.permission.PermissionQueryService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.permission.dto.PermissionQueryRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.evolboot.identity.IdentityAccessAuthorities.Permission.*;
import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * Tenant Permission Resource
 * 租户权限管理 - 租户所有者和租户员工可访问
 * 注意：租户只能查看权限，不能创建/修改/删除权限定义
 */
@RestController
@RequestMapping("/tenant/v1/permissions")
@Tag(name = "租户权限管理", description = "租户权限管理(只读)")
@AdminClient
public class TenantPermissionResourceV1 {

    private final PermissionQueryService queryService;

    public TenantPermissionResourceV1(PermissionQueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * 权限列表(树形)
     * 只返回 TENANT scope 的权限，用于租户创建角色时选择
     */
    @GetMapping("/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PAGE)
    public ResponseModel<List<Permission>> tree() {
        // 租户只能查看 TENANT scope 的权限
        return ResponseModel.ok(queryService.findAllConvertTree(PermissionScope.TENANT));
    }

    /**
     * 权限列表(分页)
     * 只返回 TENANT scope 的权限
     */
    @GetMapping
    @Operation(summary = "权限列表")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PAGE)
    public ResponseModel<Page<Permission>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction
    ) {
        PermissionQueryRequest query = PermissionQueryRequest.builder()
                .page(page)
                .limit(limit)
                .direction(direction)
                .sortField(sortField)
                .scope(PermissionScope.TENANT)  // 强制只查询 TENANT 权限
                .build();
        return ResponseModel.ok(queryService.page(query));
    }

}

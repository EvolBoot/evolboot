package org.evolboot.identity.remote.permission;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.permission.PermissionAppService;
import org.evolboot.identity.domain.permission.PermissionQueryService;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.identity.domain.permission.entity.PermissionScope;
import org.evolboot.identity.domain.permission.dto.PermissionQueryRequest;
import org.evolboot.identity.domain.permission.dto.PermissionBatchQueryRequest;
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.remote.permission.dto.CreatePermissionRequest;
import org.evolboot.identity.remote.permission.dto.UpdatePermissionRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static org.evolboot.identity.IdentityAccessAuthorities.Permission.*;
import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * Platform Permission Resource
 * 平台权限管理 - 超管和员工可访问
 */
@RestController
@RequestMapping("/admin/v1/permissions")
@Tag(name = "平台权限管理", description = "平台权限管理")
@AdminClient
public class AdminPermissionResourceV1 {

    private final PermissionAppService service;
    private final PermissionQueryService queryService;

    public AdminPermissionResourceV1(PermissionAppService service, PermissionQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    /**
     * 创建平台权限
     */
    @PostMapping
    @Operation(summary = "创建平台权限")
    @OperationLog("创建平台权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<Permission> create(@Valid @RequestBody CreatePermissionRequest request) {
        request.setScope(PermissionScope.PLATFORM);
        Permission permission = service.create(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 创建租户权限
     */
    @PostMapping("/tenant")
    @Operation(summary = "创建租户权限")
    @OperationLog("创建租户权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<Permission> createTenant(@Valid @RequestBody CreatePermissionRequest request) {
        request.setScope(PermissionScope.TENANT);
        Permission permission = service.create(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 修改平台权限
     */
    @PutMapping()
    @Operation(summary = "修改平台权限")
    @OperationLog("修改平台权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<Permission> update(@Valid @RequestBody UpdatePermissionRequest request) {
        request.setScope(PermissionScope.PLATFORM);
        Permission permission = service.update(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 修改租户权限
     */
    @PutMapping("/tenant")
    @Operation(summary = "修改租户权限")
    @OperationLog("修改租户权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<Permission> updateTenant(@Valid @RequestBody UpdatePermissionRequest request) {
        request.setScope(PermissionScope.TENANT);
        Permission permission = service.update(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 删除平台权限
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除平台权限")
    @OperationLog("删除平台权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 删除租户权限
     */
    @DeleteMapping("/tenant/{id}")
    @Operation(summary = "删除租户权限")
    @OperationLog("删除租户权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> deleteTenant(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 获取平台权限
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取平台权限")
    @OperationLog("获取平台权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<?> get(@PathVariable("id") Long id) {
        return ResponseModel.ok(queryService.findById(id));
    }

    /**
     * 获取租户权限
     */
    @GetMapping("/tenant/{id}")
    @Operation(summary = "获取租户权限")
    @OperationLog("获取租户权限")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<?> getTenant(@PathVariable("id") Long id) {
        return ResponseModel.ok(queryService.findById(id));
    }

    /**
     * 权限列表(树形)
     * 平台
     */
    @GetMapping("/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<List<Permission>> tree(
            @RequestParam(required = false) PermissionScope scope
    ) {
        return ResponseModel.ok(queryService.findAllConvertTree(PermissionScope.PLATFORM));
    }


    /**
     * 权限列表(树形)
     * 租户
     */
    @GetMapping("/tree/tenant")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<List<Permission>> treeTenant(
            @RequestParam(required = false) PermissionScope scope
    ) {
        return ResponseModel.ok(queryService.findAllConvertTree(PermissionScope.TENANT));
    }



    /**
     * 权限列表(分页)
     * 平台权限
     */
    @GetMapping
    @Operation(summary = "权限列表(平台)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Permission>> page(PermissionBatchQueryRequest request) {
        PermissionQueryRequest query = request.convert(PermissionScope.PLATFORM);
        return ResponseModel.ok(queryService.page(query));
    }

    /**
     * 权限列表(分页)
     * 租户权限
     */
    @GetMapping("/tenant")
    @Operation(summary = "权限列表(租户)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Permission>> pageTenant(PermissionBatchQueryRequest request) {
        PermissionQueryRequest query = request.convert(PermissionScope.TENANT);
        return ResponseModel.ok(queryService.page(query));
    }

    /**
     * 导入权限数据
     * 仅超管可用
     */
    @PostMapping("/import")
    @Operation(summary = "导入权限数据")
    @OperationLog("导入权限数据")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<List<Permission>> importData(String url) {
        List<Permission> permissions = service.importJsonData(url);
        return ResponseModel.ok(permissions);
    }


    /**
     * @return
     */
    @GetMapping("/current-user/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_ROLE_STAFF)
    public ResponseModel<List<Permission>> findPermissionByUserIdConvertTree() {
        return ResponseModel.ok(queryService.findPermissionByUserIdConvertTree(SecurityAccessTokenHolder.getUserId()));
    }


}

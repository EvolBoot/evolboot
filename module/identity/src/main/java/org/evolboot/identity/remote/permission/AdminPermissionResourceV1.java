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
import org.evolboot.identity.domain.permission.entity.Type;
import org.evolboot.identity.domain.permission.service.PermissionQuery;
import org.evolboot.identity.remote.permission.dto.CreatePermissionRequest;
import org.evolboot.identity.remote.permission.dto.UpdatePermissionRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.identity.IdentityAccessAuthorities.Permission.*;
import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * 15:41:42
 */
@RestController
@RequestMapping("/v1/admin/permissions")
@Tag(name = "权限", description = "权限")
@AdminClient
public class AdminPermissionResourceV1 {

    private final PermissionAppService service;
    private final PermissionQueryService queryService;

    public AdminPermissionResourceV1(PermissionAppService service, PermissionQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    /**
     * 创建权限
     *
     * @param request
     * @return
     */
    @PostMapping
    @Operation(summary = "创建权限")
    @OperationLog("创建权限")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<Permission> create(@Valid @RequestBody CreatePermissionRequest request) {
        Permission permission = service.create(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 修改权限
     *
     * @param request
     * @return
     */
    @PutMapping()
    @Operation(summary = "修改权限")
    @OperationLog("修改权限")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<Permission> update(@Valid @RequestBody UpdatePermissionRequest request) {
        Permission permission = service.update(request);
        return ResponseModel.ok(permission);
    }

    /**
     * 删除权限
     *
     * @param
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除权限")
    @OperationLog("删除权限")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }


    /**
     * 删除权限
     *
     * @param
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取权限")
    @OperationLog("获取权限")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<?> get(@PathVariable("id") Long id) {
        return ResponseModel.ok(queryService.findById(id));
    }


    /**
     * @return
     */
    @GetMapping("/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<List<Permission>> page() {
        return ResponseModel.ok(queryService.findAllConvertTree());
    }


    /**
     * @return
     */
    @GetMapping("/current-user/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_ROLE_STAFF)
    public ResponseModel<List<Permission>> findPermissionByUserIdConvertTree() {
        return ResponseModel.ok(queryService.findPermissionByUserIdConvertTree(SecurityAccessTokenHolder.getPrincipalId(), Type.menu));
    }


    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    @Operation(summary = "权限列表")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Permission>> tree(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false) String orderField,
            @RequestParam(required = false) Direction order
    ) {
        PermissionQuery query = PermissionQuery.builder()
                .page(page)
                .limit(limit)
                .order(order)
                .orderField(orderField)
                .build();
        return ResponseModel.ok(queryService.page(query));
    }

    /**
     * 创建权限
     *
     * @param url
     * @return
     */
    @PostMapping("/import")
    @Operation(summary = "导入权限数据")
    @OperationLog("导入权限数据")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<List<Permission>> importData(String url) {
        List<Permission> permissions = service.importJsonData(url);
        return ResponseModel.ok(permissions);
    }


}

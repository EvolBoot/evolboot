package org.evolboot.identity.remote.permission;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.identity.domain.permission.PermissionAppService;
import org.evolboot.identity.domain.permission.PermissionQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.identity.IdentityAccessAuthorities.Permission.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 *  15:41:42
 */
@RestController
@RequestMapping("/v1/admin/permissions")
@Tag(name = "权限", description = "权限")
@AdminClient
public class AdminPermissionResourceV1 {

    private final PermissionAppService service;

    public AdminPermissionResourceV1(PermissionAppService service) {
        this.service = service;
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
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
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
    @PutMapping("/{id}")
    @Operation(summary = "修改权限")
    @OperationLog("修改权限")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<Permission> update(@PathVariable("id") Long id, @Valid @RequestBody UpdatePermissionRequest request) {
        Permission permission = service.update(id, request);
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
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }


    /**
     * @return
     */
    @GetMapping("/tree")
    @Operation(summary = "权限列表(树形)")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<List<Permission>> page() {
        return ResponseModel.ok(service.findAllConvertTree());
    }

    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    @Operation(summary = "权限列表")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Permission>> tree(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        PermissionQuery query = PermissionQuery.builder()
                .page(page)
                .limit(limit)
                .build();
        return ResponseModel.ok(service.page(query));
    }


    /**
     * 删除批量权限
     *
     * @param ids
     * @return
     */
    /*
    @DeleteMapping
    public ResponseModel deleteBatch(
            @RequestBody List<Long> ids
    ) {
        appService.deleteBatch(ids);
        return ResponseModel.ok();
    }
    */

}

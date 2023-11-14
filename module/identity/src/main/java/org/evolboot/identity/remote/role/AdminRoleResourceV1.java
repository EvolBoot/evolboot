package org.evolboot.identity.remote.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.role.entity.Role;
import org.evolboot.identity.domain.role.service.RoleQuery;
import org.evolboot.identity.remote.role.dto.CreateRoleRequest;
import org.evolboot.identity.remote.role.dto.UpdateRoleRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.Role.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 *
 */
@RestController
@RequestMapping("/v1/admin/roles")
@Tag(name = "角色", description = "角色")
@AdminClient
public class AdminRoleResourceV1 {

    private final RoleAppService service;

    public AdminRoleResourceV1(RoleAppService service) {
        this.service = service;
    }

    /**
     * 创建角色
     *
     * @param request
     * @return
     */
    @PostMapping
    @Operation(summary = "创建角色")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel create(@Valid @RequestBody CreateRoleRequest request) {
        service.create(request.toRequest());
        return ResponseModel.ok();
    }


    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @PutMapping("")
    @Operation(summary = "修改角色")
    @OperationLog("修改角色")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(@Valid @RequestBody UpdateRoleRequest request) {
        service.update(request);
        return ResponseModel.ok();
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色")
    @OperationLog("删除角色")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 获取单个角色
     *
     * @param
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取单个角色")
    @OperationLog("获取单个角色")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<?> get(@PathVariable("id") Long id) {
        return ResponseModel.ok(service);
    }


    /**
     * 删除批量权限
     *
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
    @GetMapping
    @Operation(summary = "角色列表")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Role>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(required = false) String orderField,
            @RequestParam(required = false) Direction order,
            @RequestParam(required = false) String roleName
    ) {
        RoleQuery query = RoleQuery.builder()
                .roleName(roleName)
                .page(page)
                .limit(limit)
                .order(order)
                .orderField(orderField)
                .build();
        return ResponseModel.ok(service.page(query));
    }
}

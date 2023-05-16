package org.evolboot.identity.remote.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.role.Role;
import org.evolboot.identity.domain.role.RoleAppService;
import org.evolboot.identity.domain.role.RoleQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.Role.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 *
 */
@RestController
@RequestMapping("/v1/admin/roles")
@Tag(name = "角色", description = "角色")
@AdminClient
public class AdminRoleResourceV1 {

    private final RoleAppService appService;

    public AdminRoleResourceV1(RoleAppService appService) {
        this.appService = appService;
    }

    /**
     * 创建角色
     *
     * @param request
     * @return
     */
    @PostMapping
    @Operation(summary = "创建角色")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel create(@Valid @RequestBody CreateRoleRequest request) {
        appService.create(request.toRequest());
        return ResponseModel.ok();
    }


    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "修改角色")
    @OperationLog("修改角色")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateRoleRequest request) {
        appService.update(request.toRequest().setId(id));
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
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(@PathVariable("id") Long id) {
        appService.delete(id);
        return ResponseModel.ok();
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
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Role>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        RoleQuery query = RoleQuery.builder()
                .page(page)
                .limit(limit)
                .build();
        return ResponseModel.ok(appService.page(query));
    }
}

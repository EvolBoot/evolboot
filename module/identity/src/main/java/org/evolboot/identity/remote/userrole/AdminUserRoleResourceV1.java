package org.evolboot.identity.remote.userrole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.userrole.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.identity.domain.userrole.UserRoleQuery;
import org.evolboot.identity.remote.user.UserRoleUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.HAS_ROLE_UPDATE;
import static org.evolboot.identity.IdentityAccessAuthorities.UserRole.HAS_PAGE;
import static org.evolboot.identity.IdentityAccessAuthorities.UserRole.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 用户角色
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin")
@Tag(name = "用户角色管理", description = "用户角色管理")
@AdminClient
public class AdminUserRoleResourceV1 {

    private final UserRoleAppService service;

    public AdminUserRoleResourceV1(UserRoleAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询用户角色")
    @OperationLog("查询用户角色")
    @GetMapping("/user-roles")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<UserRole>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        UserRoleQuery query = UserRoleQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<UserRole> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个用户角色")
    @OperationLog("查询单个用户角色")
    @GetMapping("/user-roles/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<UserRole> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }


    @Operation(summary = "管理员修改员工角色")
    @OperationLog("管理员修改员工角色")
    @PutMapping("/users/{id}/role/update")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_ROLE_UPDATE)
    public ResponseModel<?> updateRole(
            @PathVariable("id") Long id,
            @RequestBody @Valid
            UserRoleUpdateRequest request
    ) {
        service.updateRole(id, request.getRoles());
        return ResponseModel.ok();
    }


}

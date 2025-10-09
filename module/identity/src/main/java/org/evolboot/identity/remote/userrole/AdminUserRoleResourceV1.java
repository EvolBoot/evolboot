package org.evolboot.identity.remote.userrole;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.userrole.dto.UserRoleQueryRequest;
import org.evolboot.identity.domain.userrole.entity.UserRole;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import org.evolboot.identity.remote.user.dto.UserRoleUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.HAS_ROLE_UPDATE;
import static org.evolboot.identity.IdentityAccessAuthorities.UserRole.HAS_PAGE;
import static org.evolboot.identity.IdentityAccessAuthorities.UserRole.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 用户角色(废弃不用)
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1")
@Tag(name = "用户角色管理", description = "用户角色管理")
@AdminClient
@Hidden
@Deprecated
public class AdminUserRoleResourceV1 {

    private final UserRoleAppService service;

    public AdminUserRoleResourceV1(UserRoleAppService service) {
        this.service = service;
    }


    @Deprecated
    @Operation(summary = "查询用户角色")
    @OperationLog("查询用户角色")
    @GetMapping("/user-roles")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<UserRole>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        UserRoleQueryRequest query = UserRoleQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<UserRole> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Deprecated
    @Operation(summary = "查询单个用户角色")
    @OperationLog("查询单个用户角色")
    @GetMapping("/user-roles/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<UserRole> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }


    @Deprecated
    @Operation(summary = "管理员修改员工角色")
    @OperationLog("管理员修改员工角色")
    @PutMapping("/users/{id}/role/update")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_ROLE_UPDATE)
    public ResponseModel<?> updateRole(
            @PathVariable("id") Long id,
            @RequestBody @Valid
            UserRoleUpdateRequest request
    ) {
        service.updateRole(id, request.getRoles());
        return ResponseModel.ok();
    }


}

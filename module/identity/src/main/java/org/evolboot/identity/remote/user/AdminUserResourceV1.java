package org.evolboot.identity.remote.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.IdRequest;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.dto.UserQueryRequest;
import org.evolboot.identity.domain.user.dto.UserBatchQueryRequest;
import org.evolboot.identity.domain.user.service.UserCreateFactory;
import org.evolboot.identity.domain.user.service.UserStateChangeService;
import org.evolboot.identity.remote.user.dto.*;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

import static org.evolboot.identity.IdentityAccessAuthorities.User.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * @author evol
 */

@RestController
@RequestMapping("/admin/v1/users")
@Tag(name = "用户账号", description = "用户账号")
@AdminClient
public class AdminUserResourceV1 {

    private final UserAppService service;
    private final UserQueryService queryService;

    public AdminUserResourceV1(UserAppService service, UserQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @Operation(summary = "当前登录用户修改资料")
    @OperationLog("当前登录用户修改资料")
    @PutMapping("/me")
    @Authenticated
    public ResponseModel<?> update(
            @RequestBody @Valid
            UserUpdateRequest request
    ) {
        // 用户修改自己的资料，不需要 tenantId 验证
        service.update(null, request.to(SecurityAccessTokenHolder.getUserId()));
        return ResponseModel.ok();
    }

    @Operation(summary = "获取当前登录用户资料")
    @GetMapping("/me")
    @Authenticated
    public ResponseModel<User> get() {
        User user = queryService.findByUserId(SecurityAccessTokenHolder.getUserId());
        return ResponseModel.ok(user);
    }


    @Operation(summary = "创建用户")
    @OperationLog("创建用户")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<User> create(
            @RequestBody @Valid
            UserCreateRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.create(SecurityAccessTokenHolder.getCurrentPrincipal(), request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(user);
    }

    @Operation(summary = "当前用户修改密码")
    @OperationLog("当前用户修改密码")
    @PutMapping("/me/password/update")
    @Authenticated
    public ResponseModel<?> updatePassword(
            @RequestBody @Valid
            UserPasswordUpdateRequest request
    ) {
        service.updatePassword(SecurityAccessTokenHolder.getUserId(), request.getOldPassword(), request.getNewPassword(), request.getConfirmPassword());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员重置用户密码")
    @OperationLog("管理员重置用户密码")
    @PutMapping("/password/reset")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PASSWORD_RESET)
    public ResponseModel<?> resetPassword(
            @RequestBody @Valid
            UserPasswordSetRequest request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.resetPassword(null, request.getId(), request.getPassword());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员修改用户资料")
    @OperationLog("管理员修改用户资料")
    @PutMapping
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            AdminUserUpdateRequest request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.update(null, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员冻结(锁定)用户")
    @OperationLog("管理员冻结(锁定)用户")
    @PutMapping("/state/lock")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_LOCK)
    public ResponseModel<?> lock(
            @RequestBody IdRequest<Long> request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.lock(null, request.getId());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员解锁用户")
    @OperationLog("管理员解锁用户")
    @PutMapping("/state/active")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_ACTIVE)
    public ResponseModel<?> active(
            @RequestBody IdRequest<Long> request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.active(null, request.getId());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员删除用户")
    @OperationLog("管理员删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.delete(null, id);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员查询用户(用户列表)")
    @GetMapping("/member")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<User>> getUsers(UserBatchQueryRequest request) {
        request.setUserIdentity(UserIdentity.ROLE_MEMBER);
        UserQueryRequest query = request.convert(SecurityAccessTokenHolder.getTenantId());
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }


    @Operation(summary = "改为测试账号")
    @OperationLog("改为测试账号")
    @PutMapping("/user-type/set-test")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<?> setUserTypeIsTest(
            @RequestBody IdRequest<Long> request
    ) {
        service.setUserType(request.getId(), UserType.TEST);
        return ResponseModel.ok();
    }

    @Operation(summary = "更改账号类型")
    @OperationLog("更改账号类型")
    @PutMapping("/user-type")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<?> updateUserType(
            @RequestBody
            UpdateUserTypeRequest request
    ) {
        service.setUserType(request.getId(), request.getUserType());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员更换用户代理")
    @OperationLog("管理员更换用户代理")
    @PutMapping("/inviter-user-id/change")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<?> changeInviterUserId(
            @RequestBody @Valid
            ChangeInviterUserIdRequest request
    ) {
        service.changeInviterUserId(request.getId(), request.getNewInviterUserId());
        return ResponseModel.ok();
    }


    @Operation(summary = "用户上下级关系重构")
    @OperationLog("用户上下级关系重构")
    @PostMapping("/relation/refactor")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<?> refactorRelation() {
        service.refactorRelation();
        return ResponseModel.ok();
    }


    @Operation(summary = "更新谷歌验证器")
    @PutMapping("/google-authenticator-secret/update")
    @Authenticated
    public ResponseModel<?> updateGoogleAuthenticatorSecret(
            @RequestBody IdRequest<Long> request
    ) {
        User user = service.updateGoogleAuthenticatorSecret(request.getId());
        return ResponseModel.ok(user.getGoogleAuthSecret());
    }


    @Operation(summary = "用户修改状态(禁用,解封)")
    @OperationLog("用户修改状态(禁用,解封)")
    @PutMapping("/state/change")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<?> changeState(
            @RequestBody @Valid
            UserStateChangeService.Request request
    ) {
        service.changeState(SecurityAccessTokenHolder.getCurrentPrincipal(), request);
        return ResponseModel.ok();
    }

    @Operation(summary = "创建员工")
    @OperationLog(value = "创建员工", excludeUnserializable = false)
    @PostMapping("/staff")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<User> createStaff(
            @RequestBody @Valid UserCreateStaffRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.create(SecurityAccessTokenHolder.getCurrentPrincipal(), request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(user);
    }

    @Operation(summary = "修改员工资料")
    @OperationLog("修改员工资料")
    @PutMapping("/staff")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> updateStaff(
            @RequestBody @Valid
            AdminUserUpdateRequest request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.update(null, request);
        return ResponseModel.ok();
    }


    @Operation(summary = "查询员工")
    @GetMapping("/staff")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<User>> getStaff(UserBatchQueryRequest request) {
        request.setUserIdentity(UserIdentity.ROLE_STAFF);
        UserQueryRequest query = request.convert((Long) null);
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }


    // ==================== 租户管理接口 ====================

    @Operation(summary = "查询租户列表")
    @GetMapping("/tenant")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<User>> getTenants(UserBatchQueryRequest request) {
        request.setUserIdentitys(List.of(UserIdentity.ROLE_TENANT_OWNER, UserIdentity.ROLE_TENANT_STAFF));
        UserQueryRequest query = request.convert((Long) null);
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }

    @Operation(summary = "查询单个租户详情")
    @GetMapping("/tenant/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<User> getTenant(@PathVariable("id") Long id) {
        User user = queryService.findByUserId(id);
        return ResponseModel.ok(user);
    }

    @Operation(summary = "创建租户")
    @OperationLog("创建租户")
    @PostMapping("/tenant")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<User> createTenant(
            @RequestBody @Valid UserCreateTenantRequest request,
            HttpServletRequest httpServletRequest
    ) {
        UserCreateFactory.Request createRequest = request.to(IpUtil.getClientIP(httpServletRequest));
        createRequest.setUserIdentity(UserIdentity.ROLE_TENANT_OWNER);
        // 创建租户时不需要 currentPrincipal，因为是超级管理员创建的，传 null
        User user = service.create(null, createRequest);
        return ResponseModel.ok(user);
    }

    @Operation(summary = "更新租户信息")
    @OperationLog("更新租户信息")
    @PutMapping("/tenant")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> updateTenant(
            @RequestBody @Valid AdminUserUpdateRequest request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.update(null, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "禁用租户")
    @OperationLog("禁用租户")
    @PutMapping("/tenant/lock")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_LOCK)
    public ResponseModel<?> lockTenant(
            @RequestBody IdRequest<Long> request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.lock(null, request.getId());
        return ResponseModel.ok();
    }

    @Operation(summary = "启用租户")
    @OperationLog("启用租户")
    @PutMapping("/tenant/active")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_ACTIVE)
    public ResponseModel<?> activeTenant(
            @RequestBody IdRequest<Long> request
    ) {
        // 超级管理员操作，不需要 tenantId 限制
        service.active(null, request.getId());
        return ResponseModel.ok();
    }

}

package org.evolboot.identity.remote.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.annotation.TenantClient;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.IdRequest;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.dto.UserQueryRequest;
import org.evolboot.identity.remote.user.dto.*;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.*;
import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * Tenant User Resource
 * 租户用户管理 - 租户所有者和租户员工可访问
 * 只能管理本租户的员工，数据自动隔离
 */
@RestController
@RequestMapping("/tenant/v1/users")
@Tag(name = "租户用户管理", description = "租户用户管理")
@TenantClient
public class TenantUserResourceV1 {

    private final UserAppService service;
    private final UserQueryService queryService;

    public TenantUserResourceV1(UserAppService service, UserQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @Operation(summary = "获取当前登录用户资料")
    @GetMapping("/me")
    @Authenticated
    public ResponseModel<User> get() {
        User user = queryService.findByUserId(SecurityAccessTokenHolder.getUserId());
        return ResponseModel.ok(user);
    }


    @Operation(summary = "当前登录用户修改资料")
    @OperationLog("当前登录用户修改资料")
    @PutMapping("/me")
    @Authenticated
    public ResponseModel<?> update(
            @RequestBody @Valid
            UserUpdateRequest request
    ) {
        // 自动设置为租户员工并关联当前租户
        service.update(SecurityAccessTokenHolder.getCurrentPrincipal(), request.to(SecurityAccessTokenHolder.getUserId()));
        return ResponseModel.ok();
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


    /**
     * 创建租户员工
     */
    @Operation(summary = "创建租户员工")
    @OperationLog(value = "创建租户员工", excludeUnserializable = false)
    @PostMapping("/staff")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_CREATE)
    public ResponseModel<User> createStaff(
            @RequestBody @Valid UserCreateStaffRequest request,
            HttpServletRequest httpServletRequest
    ) {
        // 自动设置为租户员工并关联当前租户
        User user = service.create(SecurityAccessTokenHolder.getCurrentPrincipal(), request.to(IpUtil.getClientIP(httpServletRequest), UserIdentity.ROLE_TENANT_STAFF));
        return ResponseModel.ok(user);
    }

    /**
     * 修改租户员工资料
     */
    @Operation(summary = "修改租户员工资料")
    @OperationLog("修改租户员工资料")
    @PutMapping("/staff")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_UPDATE)
    public ResponseModel<?> updateStaff(
            @RequestBody @Valid AdminUserUpdateRequest request
    ) {
        service.update(SecurityAccessTokenHolder.getCurrentPrincipal(), request);
        return ResponseModel.ok();
    }

    /**
     * 查询租户员工
     * 自动过滤本租户的员工
     */
    @Operation(summary = "查询租户员工")
    @GetMapping("/staff")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PAGE)
    public ResponseModel<Page<User>> getStaff(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction
    ) {
        // 自动获取当前租户ID，强制数据隔离
        Long tenantId = SecurityAccessTokenHolder.getTenantId();

        UserQueryRequest query = UserQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .username(username)
                .mobile(mobile)
                .email(email)
                .key(key)
                .userIdentity(UserIdentity.ROLE_TENANT_STAFF)  // 只查询租户员工
                .tenantId(tenantId)  // 强制只查询本租户的员工
                .direction(direction)
                .sortField(sortField)
                .roleId(roleId)
                .build();
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }

    /**
     * 冻结(锁定)租户员工
     */
    @Operation(summary = "冻结(锁定)租户员工")
    @OperationLog("冻结(锁定)租户员工")
    @PutMapping("/state/lock")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_LOCK)
    public ResponseModel<?> lock(
            @RequestBody IdRequest<Long> request
    ) {
        service.lock(SecurityAccessTokenHolder.getCurrentPrincipal(), request.getId());
        return ResponseModel.ok();
    }

    /**
     * 解锁租户员工
     */
    @Operation(summary = "解锁租户员工")
    @OperationLog("解锁租户员工")
    @PutMapping("/state/active")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_ACTIVE)
    public ResponseModel<?> active(
            @RequestBody IdRequest<Long> request
    ) {
        service.active(SecurityAccessTokenHolder.getCurrentPrincipal(), request.getId());
        return ResponseModel.ok();
    }

    /**
     * 删除租户员工
     */
    @Operation(summary = "删除租户员工")
    @OperationLog("删除租户员工")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(SecurityAccessTokenHolder.getCurrentPrincipal(), id);
        return ResponseModel.ok();
    }

    /**
     * 重置租户员工密码
     */
    @Operation(summary = "重置租户员工密码")
    @OperationLog("重置租户员工密码")
    @PutMapping("/password/reset")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + HAS_PASSWORD_RESET)
    public ResponseModel<?> resetPassword(
            @RequestBody @Valid UserPasswordSetRequest request
    ) {
        service.resetPassword(SecurityAccessTokenHolder.getCurrentPrincipal(), request.getId(), request.getPassword());
        return ResponseModel.ok();
    }

}

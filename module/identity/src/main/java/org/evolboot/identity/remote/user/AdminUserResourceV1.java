package org.evolboot.identity.remote.user;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserQuery;
import org.evolboot.identity.domain.user.service.UserStatusChangeService;
import org.evolboot.identity.domain.user.UserType;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.lang.UserIdentity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/admin/users")
@Tag(name = "用户账号", description = "用户账号")
@AdminClient
public class AdminUserResourceV1 {

    private final UserAppService service;

    public AdminUserResourceV1(UserAppService service) {
        this.service = service;
    }

    @Operation(summary = "当前登录用户修改资料")
    @OperationLog("当前登录用户修改资料")
    @PutMapping("/me")
    @Authenticated
    public ResponseModel<?> update(
            @RequestBody @Valid
                    UserUpdateRequest request
    ) {
        service.update(SecurityAccessTokenHolder.getPrincipalId(), request.to());
        return ResponseModel.ok();
    }

    @Operation(summary = "获取当前登录用户资料")
    @GetMapping("/me")
    @Authenticated
    public ResponseModel<User> get() {
        User user = service.findByUserId(SecurityAccessTokenHolder.getPrincipalId());
        return ResponseModel.ok(user);
    }


    @Operation(summary = "创建用户")
    @OperationLog("创建用户")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<User> create(
            @RequestBody @Valid
                    UserCreateRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.create(request.to(IpUtil.getClientIP(httpServletRequest)));
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
        service.updatePassword(SecurityAccessTokenHolder.getPrincipalId(), request.getOldPassword(), request.getNewPassword(), request.getConfirmPassword());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员重置用户密码")
    @OperationLog("管理员重置用户密码")
    @PutMapping("/{id}/password/reset")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PASSWORD_RESET)
    public ResponseModel<?> resetPassword(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    UserPasswordSetRequest request
    ) {
        service.resetPassword(id, request.getPassword());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员修改用户资料")
    @OperationLog("管理员修改用户资料")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    AdminUserUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员冻结(锁定)用户")
    @OperationLog("管理员冻结(锁定)用户")
    @PutMapping("/{id}/status/lock")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_LOCK)
    public ResponseModel<?> lock(
            @PathVariable("id") Long id
    ) {
        service.lock(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员解锁用户")
    @OperationLog("管理员解锁用户")
    @PutMapping("/{id}/status/active")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_ACTIVE)
    public ResponseModel<?> active(
            @PathVariable("id") Long id
    ) {
        service.active(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员删除用户")
    @OperationLog("管理员删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员查询用户(用户列表)")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<User>> getUsers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false, name = "user_id") Long userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, name = "register_ip") String registerIp,
            @RequestParam(required = false, name = "inviter_user_id") Long inviterUserId,
            @RequestParam(required = false, name = "user_type") UserType userType,
            @RequestParam(required = false, name = "user_identity") UserIdentity userIdentity
    ) {
        UserQuery query = UserQuery
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .userIdentity(userIdentity)
                .username(username)
                .mobile(mobile)
                .email(email)
                .registerIp(registerIp)
                .inviterUserId(inviterUserId)
                .userType(userType)
                .build();
        Page<User> userPage = service.page(query);
        return ResponseModel.ok(userPage);
    }


    @Operation(summary = "改为测试账号")
    @OperationLog("改为测试账号")
    @PutMapping("/{id}/user-type/set-test")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> setUserTypeIsTest(
            @PathVariable("id") Long id
    ) {
        service.setUserType(id, UserType.TEST);
        return ResponseModel.ok();
    }

    @Operation(summary = "更改账号类型")
    @OperationLog("更改账号类型")
    @PutMapping("/{id}/user-type")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> updateUserType(
            @PathVariable("id") Long id,
            @RequestBody
                    UpdateUserTypeRequest request
    ) {
        service.setUserType(id, request.getUserType());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员更换用户代理")
    @OperationLog("管理员更换用户代理")
    @PutMapping("/{id}/inviter-user-id/change")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> changeInviterUserId(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    ChangeInviterUserIdRequest request
    ) {
        service.changeInviterUserId(id, request.getNewInviterUserId());
        return ResponseModel.ok();
    }


    @Operation(summary = "用户上下级关系重构")
    @OperationLog("用户上下级关系重构")
    @PostMapping("/relation/refactor")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> refactorRelation() {
        service.refactorRelation();
        return ResponseModel.ok();
    }


    @Operation(summary = "更新谷歌验证器")
    @PutMapping("/{id}/google-authenticator-secret/update")
    @Authenticated
    public ResponseModel<?> updateGoogleAuthenticatorSecret(
            @PathVariable("id") Long id
    ) {
        User user = service.updateGoogleAuthenticatorSecret(id);
        return ResponseModel.ok(user.getGoogleAuthSecret());
    }


    @Operation(summary = "用户修改状态(禁用,解封)")
    @OperationLog("用户修改状态(禁用,解封)")
    @PutMapping("/status/change")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> changeStatus(
            @RequestBody @Valid
                    UserStatusChangeService.Request request
    ) {
        service.changeStatus(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "创建员工")
    @OperationLog("创建员工")
    @PostMapping("/staff")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<User> createStaff(
            @RequestBody @Valid UserCreateStaffRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.create(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(user);
    }
}

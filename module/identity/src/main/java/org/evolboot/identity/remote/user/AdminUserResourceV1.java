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
import org.evolboot.identity.domain.user.service.UserStateChangeService;
import org.evolboot.identity.remote.user.dto.*;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static org.evolboot.identity.IdentityAccessAuthorities.User.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/admin/users")
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
        service.update(request.to(SecurityAccessTokenHolder.getPrincipalId()));
        return ResponseModel.ok();
    }

    @Operation(summary = "获取当前登录用户资料")
    @GetMapping("/me")
    @Authenticated
    public ResponseModel<User> get() {
        User user = queryService.findByUserId(SecurityAccessTokenHolder.getPrincipalId());
        return ResponseModel.ok(user);
    }


    @Operation(summary = "创建用户")
    @OperationLog("创建用户")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
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
    @PutMapping("/password/reset")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PASSWORD_RESET)
    public ResponseModel<?> resetPassword(
            @RequestBody @Valid
            UserPasswordSetRequest request
    ) {
        service.resetPassword(request.getId(), request.getPassword());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员修改用户资料")
    @OperationLog("管理员修改用户资料")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            AdminUserUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员冻结(锁定)用户")
    @OperationLog("管理员冻结(锁定)用户")
    @PutMapping("/state/lock")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_LOCK)
    public ResponseModel<?> lock(
            @RequestBody IdRequest<Long> request
    ) {
        service.lock(request.getId());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员解锁用户")
    @OperationLog("管理员解锁用户")
    @PutMapping("/state/active")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_ACTIVE)
    public ResponseModel<?> active(
            @RequestBody IdRequest<Long> request
    ) {
        service.active(request.getId());
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员删除用户")
    @OperationLog("管理员删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "管理员查询用户(用户列表)")
    @GetMapping("/member")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<User>> getUsers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String registerIp,
            @RequestParam(required = false) Long inviterUserId,
            @RequestParam(required = false) UserType userType,

            @Parameter(description = "排序字段")
            @RequestParam(required = false) String sortField,

            @Parameter(description = "排序方向")
            @RequestParam(required = false) Direction direction
    ) {
        UserQueryRequest query = UserQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .username(username)
                .mobile(mobile)
                .email(email)
                .registerIp(registerIp)
                .inviterUserId(inviterUserId)
                .userIdentity(UserIdentity.ROLE_MEMBER)
                .userType(userType)
                .direction(direction)
                .sortField(sortField)
                .build();
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }


    @Operation(summary = "改为测试账号")
    @OperationLog("改为测试账号")
    @PutMapping("/user-type/set-test")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> setUserTypeIsTest(
            @RequestBody IdRequest<Long> request
    ) {
        service.setUserType(request.getId(), UserType.TEST);
        return ResponseModel.ok();
    }

    @Operation(summary = "更改账号类型")
    @OperationLog("更改账号类型")
    @PutMapping("/user-type")
    @PreAuthorize(HAS_ROLE_ADMIN)
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
    @PreAuthorize(HAS_ROLE_ADMIN)
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
    @PreAuthorize(HAS_ROLE_ADMIN)
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
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<?> changeState(
            @RequestBody @Valid
            UserStateChangeService.Request request
    ) {
        service.changeState(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "创建员工")
    @OperationLog(value = "创建员工", excludeUnserializable = false)
    @PostMapping("/staff")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<User> createStaff(
            @RequestBody @Valid UserCreateStaffRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.create(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(user);
    }


    @Operation(summary = "修改员工资料")
    @OperationLog("修改员工资料")
    @PutMapping("/staff")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> updateStaff(
            @RequestBody @Valid
            AdminUserUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }


    @Operation(summary = "查询员工")
    @GetMapping("/staff")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
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
        UserQueryRequest query = UserQueryRequest
                .builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .username(username)
                .mobile(mobile)
                .email(email)
                .key(key)
                .userIdentity(UserIdentity.ROLE_STAFF)
                .direction(direction)
                .sortField(sortField)
                .roleId(roleId)
                .build();
        Page<User> userPage = queryService.page(query);
        return ResponseModel.ok(userPage);
    }

}

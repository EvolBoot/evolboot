package org.evolboot.identity.remote.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.IpUtil;
import org.evolboot.identity.acl.client.IdentityCaptchaClient;
import org.evolboot.identity.acl.client.IdentityConfigClient;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.UserQueryService;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.evolboot.identity.domain.user.service.UserQuery;
import org.evolboot.identity.domain.user.service.UserSecurityPasswordUpdateService;
import org.evolboot.identity.remote.user.dto.TokenResponse;
import org.evolboot.identity.remote.user.dto.UserPasswordUpdateRequest;
import org.evolboot.identity.remote.user.dto.UserRegisterRequest;
import org.evolboot.identity.remote.user.dto.UserUpdateRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.email.EmailMessageTag;
import org.evolboot.shared.lang.UserIdentity;
import org.evolboot.shared.sms.SmsMessageTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/api/users")
@Tag(name = "用户账号", description = "用户账号")
@ApiClient
public class AppUserResourceV1 {

    private final UserAppService service;
    private final IdentityConfigClient identityConfigClient;
    private final IdentityCaptchaClient identityCaptchaClient;

    private final UserQueryService queryService;

    @Autowired
    private UserRepository userRepository;

    public AppUserResourceV1(UserAppService service, IdentityConfigClient identityConfigClient, IdentityCaptchaClient identityCaptchaClient, UserQueryService queryService) {
        this.service = service;
        this.identityConfigClient = identityConfigClient;
        this.identityCaptchaClient = identityCaptchaClient;
        this.queryService = queryService;
    }

    @Operation(summary = "当前登录用户修改资料")
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


    @Operation(summary = "注册")
    @OperationLog("注册")
    @PostMapping("/register")
    public ResponseModel<User> register(
            @RequestBody @Valid
            UserRegisterRequest request,
            HttpServletRequest httpServletRequest
    ) {
        User user = service.register(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(user);
    }

    @Operation(summary = "当前用户修改密码")
    @PutMapping("/me/password/update")
    @Authenticated
    public ResponseModel<?> updatePassword(
            @RequestBody @Valid
            UserPasswordUpdateRequest request
    ) {
        service.updatePassword(
                SecurityAccessTokenHolder.getPrincipalId(),
                request.getOldPassword(),
                request.getNewPassword(),
                request.getConfirmPassword()
        );
        return ResponseModel.ok();
    }


    @Operation(summary = "修改安全密码")
    @PutMapping("/me/security-password/update")
    @Authenticated
    public ResponseModel<?> resetPassword(
            @RequestBody @Valid UserSecurityPasswordUpdateService.Request request) {
        service.updateSecurityPassword(SecurityAccessTokenHolder.getPrincipalId(), request);
        return ResponseModel.ok();
    }


    @Operation(summary = "重置安全密码")
    @PostMapping("/security-password/reset")
    @Authenticated
    public ResponseModel<?> update(@RequestBody @Valid AppSecurityPasswordResetRequest request) {
        service.resetSecurityPassword(
                request.to(
                        SecurityAccessTokenHolder.getPrincipalId(),
                        SecurityAccessTokenHolder.getToken()
                )
        );
        return ResponseModel.ok();
    }


    @Operation(summary = "已登录用户获取短信验证码")
    @PostMapping("/sms-captcha")
    @Authenticated
    public ResponseModel<TokenResponse> resetTradePasswordSmsCaptcha(
            HttpServletRequest servletRequest) {
        User user = queryService.findByUserId(SecurityAccessTokenHolder.getPrincipalId());
        // 您未设置手机号
        Assert.notBlank(user.getMobile(), "您的手机号未设置");
        String token =
                identityCaptchaClient.sendMobileCaptcha(
                        user.getMobilePrefix(),
                        user.getMobile(),
                        SmsMessageTag.CHECK,
                        IpUtil.getClientIP(servletRequest),
                        SecurityAccessTokenHolder.getToken()
                );
        return ResponseModel.ok(new TokenResponse(token));
    }


    @Operation(summary = "已登录用户获取邮箱验证码")
    @PostMapping("/email-captcha")
    @Authenticated
    public ResponseModel<TokenResponse> resetTradePasswordEmailCaptcha(
            HttpServletRequest servletRequest) {
        User user = queryService.findByUserId(SecurityAccessTokenHolder.getPrincipalId());
        // 您未设置邮箱
        Assert.notBlank(user.getEmail(), "邮箱未设置");
        String token =
                identityCaptchaClient.sendEmailCaptcha(
                        user.getEmail(),
                        EmailMessageTag.CHECK,
                        IpUtil.getClientIP(servletRequest),
                        SecurityAccessTokenHolder.getToken());
        return ResponseModel.ok(new TokenResponse(token));
    }


    @Operation(summary = "查看用户头像")
    @GetMapping("/avatar/{id}")
    public void getAvatar(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String avatar = queryService.findAvatarByUserId(id);
        response.sendRedirect(UserConfiguration.getValue().getDefaultAvatar());
    }


    @Operation(summary = "count")
    @OperationLog(value = "count")
    @PostMapping("/count")
    public ResponseModel<?> resetLoginPassword() {
        return ResponseModel.ok(userRepository.count(UserQuery.builder().userIdentity(UserIdentity.ROLE_MEMBER).build()));
    }
}

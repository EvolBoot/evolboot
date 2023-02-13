package org.evolboot.identity.remote.user;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.IpUtil;
import org.evolboot.identity.acl.client.IdentityCaptchaClient;
import org.evolboot.identity.acl.client.IdentityConfigClient;
import org.evolboot.identity.domain.user.User;
import org.evolboot.identity.domain.user.UserAppService;
import org.evolboot.identity.domain.user.UserConfiguration;
import org.evolboot.identity.domain.user.UserSecurityPasswordUpdateService;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.email.MessageTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public AppUserResourceV1(UserAppService service, IdentityConfigClient identityConfigClient, IdentityCaptchaClient identityCaptchaClient) {
        this.service = service;
        this.identityConfigClient = identityConfigClient;
        this.identityCaptchaClient = identityCaptchaClient;
    }

    @Operation(summary = "当前登录用户修改资料")
    @PutMapping("/me")
    @Authenticated
    public ResponseModel<?> update(
            @RequestBody @Valid
                    UserUpdateRequest request
    ) {
        service.update(SecurityAccessTokenHolder.getUserId(), request.to());
        return ResponseModel.ok();
    }

    @Operation(summary = "获取当前登录用户资料")
    @GetMapping("/me")
    @Authenticated
    public ResponseModel<User> get() {
        User user = service.findByUserId(SecurityAccessTokenHolder.getUserId());
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
                SecurityAccessTokenHolder.getUserId(),
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
        service.updateSecurityPassword(SecurityAccessTokenHolder.getUserId(), request);
        return ResponseModel.ok();
    }


    @Operation(summary = "重置资金(安全,交易)密码")
    @PostMapping("/security-password/reset")
    @Authenticated
    public ResponseModel<?> update(@RequestBody @Valid AppSecurityPasswordResetRequest request) {
        service.resetSecurityPassword(
                request.to(
                        SecurityAccessTokenHolder.getUserId(),
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
        User user = service.findByUserId(SecurityAccessTokenHolder.getUserId());
        // 您未设置手机号
        Assert.notBlank(user.getMobile(), "您的手机号未设置");
        String token =
                identityCaptchaClient.sendMobileCaptcha(
                        user.getMobilePrefix(),
                        user.getMobile(),
                        org.evolboot.shared.sms.MessageTag.CHECK,
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
        User user = service.findByUserId(SecurityAccessTokenHolder.getUserId());
        // 您未设置邮箱
        Assert.notBlank(user.getEmail(), "邮箱未设置");
        String token =
                identityCaptchaClient.sendEmailCaptcha(
                        user.getEmail(),
                        MessageTag.CHECK,
                        IpUtil.getClientIP(servletRequest),
                        SecurityAccessTokenHolder.getToken());
        return ResponseModel.ok(new TokenResponse(token));
    }


    @Operation(summary = "重置登录密码")
    @OperationLog(value = "重置登录密码", serializable = false)
    @PostMapping("/password/reset")
    public ResponseModel<?> resetLoginPassword(
            @RequestBody @Valid UserResetPasswordRequest request,
            HttpServletRequest httpServletRequest) {
        service.resetPassword(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok();
    }


    @Operation(summary = "查看用户头像")
    @GetMapping("/avatar/{id}")
    public void getAvatar(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String avatar = service.findAvatarByUserId(id);
        response.sendRedirect(UserConfiguration.getValue().getDefaultAvatar());
    }
}

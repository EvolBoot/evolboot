package org.evolboot.security.accesstoken.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import org.evolboot.security.accesstoken.domain.AccessToken;
import org.evolboot.security.accesstoken.domain.AccessTokenAppService;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/api/security")
@Tag(name = "访问令牌", description = "访问令牌")
@ApiClient
public class AppSessionUserResourceV1 {

    private final AccessTokenAppService service;

    public AppSessionUserResourceV1(AccessTokenAppService service) {
        this.service = service;
    }

    @Operation(summary = "认证(登录)")
    @PostMapping("/authenticate")
    public ResponseModel<?> authenticate(
            HttpServletRequest servletRequest,
            @Valid @RequestBody
            AppAccessTokenAuthenticateTokenRequest accessTokenAuthenticateToken
    ) {

        AccessToken accessToken = service.authenticate(accessTokenAuthenticateToken.to(IpUtil.getClientIP(servletRequest)));
        return ResponseModel.ok(accessToken.getToken());
    }

    @Operation(summary = "注销")
    @GetMapping("/logout")
    @Authenticated
    public ResponseModel<?> logout() {
        service.logout(SecurityAccessTokenHolder.getToken());
        SecurityAccessTokenHolder.logout();
        return ResponseModel.ok();
    }

    @Operation(summary = "刷新会话时间")
    @GetMapping("/refresh")
    @Authenticated
    public ResponseModel<?> refresh() {
        service.refresh(SecurityAccessTokenHolder.getToken());
        return ResponseModel.ok();
    }

    @Operation(summary = "注册并登录")
    @OperationLog(value = "注册并登录", serializable = false)
    @PostMapping("/register")
    public ResponseModel<?> register(
            @RequestBody @Valid
            UserRegisterAndGetAccessTokenRequest request,
            HttpServletRequest httpServletRequest
    ) {
        AccessToken accessToken = service.registerAndGetAccessToken(request.to(IpUtil.getClientIP(httpServletRequest)));
        return ResponseModel.ok(accessToken.getToken());
    }
}

package org.evolboot.security.accesstoken.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
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
@RequestMapping("/v1/admin/security")
@Tag(name = "访问令牌", description = "访问令牌")
@AdminClient
public class AdminSessionUserResourceV1 {

    private final AccessTokenAppService service;


    public AdminSessionUserResourceV1(AccessTokenAppService service) {
        this.service = service;
    }

    /*
      @GetMapping("/image-captcha/{width}/{height}")
      @Operation(summary = "获取登录图片验证码")
      public ResponseModel<ImageCaptchaCreateFactory.Response> create(
              @PathVariable("width") Integer width,
              @PathVariable("height") Integer height
      ) {
          ImageCaptchaCreateFactory.Response imageCaptchaResponse = ImageCaptchaClient.create(width, height);
          return ResponseModel.ok(imageCaptchaResponse);
      }
  */
    @Operation(summary = "认证(登录)")
    @PostMapping("/authenticate")
    public ResponseModel<?> login(
            HttpServletRequest servletRequest,
            @Valid @RequestBody
            AdminAccessTokenAuthenticateTokenRequest accessTokenAuthenticateToken
    ) {
        AccessToken accessToken = service.authenticate(accessTokenAuthenticateToken.to(IpUtil.getClientIP(servletRequest)));
        return ResponseModel.ok(accessToken.getToken());
    }

    @OperationLog("退出(注销)")
    @Operation(summary = "退出(注销)")
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

}

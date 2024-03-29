package org.evolboot.security.accesstoken.remote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.security.accesstoken.domain.AccessTokenAuthenticateToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.shared.lang.DeviceType;

/**
 * @author evol
 */
@Setter
@Getter
public class AppAccessTokenAuthenticateTokenRequest {

    @Schema(title = "用户名/邮箱/手机号", example = "evol")
    private String username;

    @Schema(title = "密码", example = "2CbZMXS2fb/hC5OyMwmB9DPP+ZpvroothKsKTp2uOAqloHVqE3vbSzxvQJWKB+mCGg4x1jer+coJvsF4EJcnCeVwMiLrs3ElZlJ52vsDowDzxr0ybaua2IOL2fhmfPmdw8imDVHk1zssFQJvp2QtsH390jlHTEn9t6rpPaZIlPM=")
    private String password;

    private String captchaToken;

    private String captchaCode;


    private AuthenticationTokenType authenticationTokenType;

    private DeviceType deviceType;

    public AccessTokenAuthenticateToken to(String ip) {
        return AccessTokenAuthenticateToken.builder()
                .username(username)
                .password(password)
                .captchaToken(captchaToken)
                .captchaCode(captchaCode)
                .authenticationTokenType(authenticationTokenType)
                .deviceType(deviceType)
                .ip(ip)
                .build();
    }

}

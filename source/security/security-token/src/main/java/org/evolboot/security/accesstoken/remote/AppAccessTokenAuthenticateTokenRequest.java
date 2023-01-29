package org.evolboot.security.accesstoken.remote;

import org.evolboot.security.accesstoken.domain.AccessTokenAuthenticateToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public class AppAccessTokenAuthenticateTokenRequest {

    @Schema(description = "用户名/邮箱/手机号", example = "zhangsan")
    private String username;

    @Schema(description = "密码", example = "2CbZMXS2fb/hC5OyMwmB9DPP+ZpvroothKsKTp2uOAqloHVqE3vbSzxvQJWKB+mCGg4x1jer+coJvsF4EJcnCeVwMiLrs3ElZlJ52vsDowDzxr0ybaua2IOL2fhmfPmdw8imDVHk1zssFQJvp2QtsH390jlHTEn9t6rpPaZIlPM=")
    private String password;


    private String mobileCaptchaToken;

    private String mobileCaptchaCode;


    private AuthenticationTokenType authenticationTokenType;

    public AccessTokenAuthenticateToken to() {
        return AccessTokenAuthenticateToken.builder()
                .username(username)
                .password(password)
                .mobileCaptchaToken(mobileCaptchaToken)
                .mobileCaptchaCode(getMobileCaptchaCode())
                .authenticationTokenType(authenticationTokenType)
                .build();
    }

}

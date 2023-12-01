package org.evolboot.security.accesstoken.remote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.security.accesstoken.domain.AccessTokenAuthenticateToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;

/**
 * @author evol
 */
@Setter
@Getter
public class AdminAccessTokenAuthenticateTokenRequest {


    @Schema(title = "用户名/邮箱/手机号", example = "root")
    private String username;

    @Schema(title = "密码", example = "0hYEA6xUVY6YsEqWlqq4fU/fsXVKfjyFJy15mxyp0RSmJ2AhvF2fPotQ4jrBcretecpe5z2rPNIbEklMWa9a1P1woJxTq7AApdBzSTSazBKBJPOQ9OLd6JPHFwEuHVSE6TG8tDDXoo4cJFCeuoyDvd9q75kkpUBOxZAI1qTtWL0=")
    private String password;

    private String googleAuthenticatorCode;


    private String captchaToken;

    private String captchaCode;


    public AccessTokenAuthenticateToken to(String ip) {
        return AccessTokenAuthenticateToken.builder()
                .username(username)
                .password(password)
                .googleAuthenticatorCode(googleAuthenticatorCode)
                .authenticationTokenType(AuthenticationTokenType.IMAGE_CAPTCHA)
                .captchaToken(captchaToken)
                .captchaCode(captchaCode)
                .ip(ip)
                .build();
    }

}

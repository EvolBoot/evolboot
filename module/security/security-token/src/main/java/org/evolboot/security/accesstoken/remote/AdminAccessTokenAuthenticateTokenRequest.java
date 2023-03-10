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
public class AdminAccessTokenAuthenticateTokenRequest {


    @Schema(description = "用户名/邮箱/手机号", example = "root")
    private String username;

    @Schema(description = "密码", example = "0hYEA6xUVY6YsEqWlqq4fU/fsXVKfjyFJy15mxyp0RSmJ2AhvF2fPotQ4jrBcretecpe5z2rPNIbEklMWa9a1P1woJxTq7AApdBzSTSazBKBJPOQ9OLd6JPHFwEuHVSE6TG8tDDXoo4cJFCeuoyDvd9q75kkpUBOxZAI1qTtWL0=")
    private String password;

    private String googleAuthenticatorCode;


    public AccessTokenAuthenticateToken to() {
        return AccessTokenAuthenticateToken.builder()
                .username(username)
                .password(password)
                .googleAuthenticatorCode(googleAuthenticatorCode)
                .authenticationTokenType(AuthenticationTokenType.GOOGLE_AUTHENTICATOR)
                .build();
    }

}

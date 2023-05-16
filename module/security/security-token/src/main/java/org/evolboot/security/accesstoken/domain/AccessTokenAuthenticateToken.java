package org.evolboot.security.accesstoken.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.shared.lang.DeviceType;

/**
 * @author evol
 */
@Getter
@Setter
@Builder
public class AccessTokenAuthenticateToken {

    @Schema(description = "用户名/邮箱/手机号", example = "root")
    private String username;

    @Schema(description = "密码", example = "0hYEA6xUVY6YsEqWlqq4fU/fsXVKfjyFJy15mxyp0RSmJ2AhvF2fPotQ4jrBcretecpe5z2rPNIbEklMWa9a1P1woJxTq7AApdBzSTSazBKBJPOQ9OLd6JPHFwEuHVSE6TG8tDDXoo4cJFCeuoyDvd9q75kkpUBOxZAI1qTtWL0=")
    private String password;

    String mobilePrefix;

    private String mobileCaptchaToken;

    private String mobileCaptchaCode;

    private String googleAuthenticatorCode;

    private AuthenticationTokenType authenticationTokenType;


    private DeviceType deviceType;

    private String ip;

}
